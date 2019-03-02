package by.guzov.finaltask.util;

import by.guzov.finaltask.util.validation.StringValidator;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public final class MailBot {
    private static final String TLS = "mail.smtp.starttls.enable";
    private static final String HOST = "mail.smtp.host";
    private static final String USER = "mail.smtp.user";
    private static final String PASSWORD = "mail.smtp.password";
    private static final String PORT = "mail.smtp.port";
    private static final String AUTH = "mail.smtp.auth";
    private static final String PROTOCOL = "smtp";
    private static final String PROPERTIES_PATH = "/mail_bot.properties";
    private static String HOST_VALUE;
    private static String FROM_VALUE;
    private static String PASS_VALUE;
    private static Properties properties;
    private static Properties mailProperties;
    private static MailBot INSTANCE = new MailBot();

    public static MailBot getInstance() {
        return INSTANCE;
    }

    private MailBot() {
        try {
            properties = new Properties();
            properties.load(getClass().getResourceAsStream(PROPERTIES_PATH));
            HOST_VALUE = properties.getProperty(HOST);
            FROM_VALUE = properties.getProperty(USER);
            PASS_VALUE = properties.getProperty(PASSWORD);
            mailProperties = System.getProperties();
            mailProperties.put(TLS, properties.getProperty(TLS));
            mailProperties.put(PORT, properties.getProperty(PORT));
            mailProperties.put(AUTH, properties.getProperty(AUTH));
            mailProperties.put(HOST, HOST_VALUE);
            mailProperties.put(USER, FROM_VALUE);
            mailProperties.put(PASSWORD, PASS_VALUE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendEmailWithCode(String code, String email) throws MessagingException {
        if (!StringValidator.validate(email, 3, StringValidator.EMAIL_PATTERN)) {
            throw new IllegalArgumentException("incorrect email");
        }
        if (properties == null) {
            new MailBot();
        }
        Session session = Session.getDefaultInstance(mailProperties, null);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(FROM_VALUE));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        message.setSubject("Password recovery");
        message.setContent("Your secret code: " + code + ". It will be available for the next 10 minutes"
                , "text/html");
        Transport transport = session.getTransport(PROTOCOL);
        transport.connect(HOST_VALUE, FROM_VALUE, PASS_VALUE);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}

package by.guzov.finaltask.util;

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
    private static Properties properties;
    private static MailBot INSTANCE = new MailBot();

    public static MailBot getInstance() {
        return INSTANCE;
    }

    private MailBot() {
        try {
            properties = new Properties();
            properties.load(getClass().getResourceAsStream(PROPERTIES_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendEmailWithCode(String code, String email) throws MessagingException {
        if (properties == null) {
            new MailBot();
        }
        String host = properties.getProperty(HOST);
        String from = properties.getProperty(USER);
        String pass = properties.getProperty(PASSWORD);
        Properties props = System.getProperties();
        props.put(TLS, properties.getProperty(TLS));
        props.put(PORT, properties.getProperty(PORT));
        props.put(AUTH, properties.getProperty(AUTH));
        props.put(HOST, host);
        props.put(USER, from);
        props.put(PASSWORD, pass);
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        message.setSubject("Password recovery");
        message.setContent("Your secret code: " + code + ". It will be available for the next 10 minutes"
                , "text/html");
        Transport transport = session.getTransport(PROTOCOL);
        transport.connect(host, from, pass);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}

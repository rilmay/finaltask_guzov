package by.guzov.finaltask.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Encryptor {
    private static final Logger LOGGER = LogManager.getLogger(Encryptor.class);

    private Encryptor() {
    }

    public static String shaEncryption(String input) {
        try {
            byte[] hexHash = MessageDigest.getInstance("SHA-256").digest(input.getBytes(StandardCharsets.UTF_8));
            return IntStream.range(0, hexHash.length).mapToObj(i -> Integer.toHexString(0xff & hexHash[i]))
                    .map(s -> (s.length() == 1) ? "0" + s : s).collect(Collectors.joining());
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Failed when encrypting");
            throw new IllegalStateException(e);
        }
    }

}

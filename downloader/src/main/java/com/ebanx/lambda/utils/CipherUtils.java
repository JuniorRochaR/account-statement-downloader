package com.ebanx.lambda.utils;

import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CipherUtils {

    private static final Logger LOG = LoggerFactory.getLogger(CipherUtils.class);

    private static final String ALGORITHM = "PBEWithMD5AndDES";
    private static final char[] HEX_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final Cipher ENCRYPTOR;
    private static final Cipher DECRYPTOR;
    private static final SecretKey PBE_KEY;
    private static final PBEParameterSpec PBE_PARAM_SPEC;

    private static final byte[] SALT = {(byte)0xc71, (byte)0x13, (byte)0x2a, (byte)0x8c, (byte)0x7e, (byte)0xc8, (byte)0xee, (byte)0xf9};

    static {
        try {
            PBE_PARAM_SPEC = new PBEParameterSpec(SALT, 7);

            PBEKeySpec pbeKeySpec = new PBEKeySpec(("5%@familyGuy*Vs*SouthPark$&8").toCharArray());
            SecretKeyFactory keyFac = SecretKeyFactory.getInstance(ALGORITHM);
            PBE_KEY = keyFac.generateSecret(pbeKeySpec);

            ENCRYPTOR = Cipher.getInstance(ALGORITHM);
            DECRYPTOR = Cipher.getInstance(ALGORITHM);
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException(e);
        }
    }

    private CipherUtils() {
        // Utility class
    }

    public static String decrypt(String text) {
        try {
            byte[] inBytes = toByteArray(text);
            byte[] outBytes;
            synchronized (DECRYPTOR) {
                DECRYPTOR.init(Cipher.DECRYPT_MODE, PBE_KEY, PBE_PARAM_SPEC);
                outBytes = DECRYPTOR.doFinal(inBytes);
            }
            return new String(outBytes);
        } catch (Exception e) {
            throw new RuntimeException("Fail on decrypt payee id.", e);
        }
    }

    public static byte[] toByteArray(String hexString) {
        byte[] array = new byte[hexString.length() / 2];
        for (int i = 0; i < (hexString.length() / 2); i++) {
            byte firstNibble = Byte.parseByte(hexString.substring(2 * i, 2 * i + 1), 16);
            byte secondNibble = Byte.parseByte(hexString.substring(2 * i + 1, 2 * i + 2), 16);
            int finalByte = (secondNibble & 0xff) | (firstNibble << 4);
            array[i] = (byte)finalByte;
        }
        return array;
    }

}
package com.enggcell.utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;

public class EncryptUtils {

    // Retrive methods of MAFactoryHelper
    public String encodeMD5(String plaintext) {
        String sessionid = plaintext;
        byte[] defaultBytes = sessionid.getBytes();
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(defaultBytes);
            byte messageDigest[] = algorithm.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            }
            String foo = messageDigest.toString();
            // Writer.print("sessionid " + sessionid + " md5 version is "
            // + hexString.toString());
            sessionid = hexString + "";
        } catch (NoSuchAlgorithmException nsae) {
        }
        return sessionid;

    }

}

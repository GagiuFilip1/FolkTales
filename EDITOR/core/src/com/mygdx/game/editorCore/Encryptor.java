package com.mygdx.game.editorCore;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 * Created by Gagiu on 4/23/2017.
 */
public class Encryptor
{
  private byte[] encrypted;

  private String encryptedtext;
  private String decrypted;

  public String secure(String pInput) {

    try
    {
      String text = pInput;
      String key = "Bar12345Bar12345"; // 128 bit key
      // Create key and cipher
      Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
      Cipher cipher = Cipher.getInstance("AES");
      // encrypt the text
      cipher.init(Cipher.ENCRYPT_MODE, aesKey);
      byte[] encrypted = cipher.doFinal(text.getBytes());

      StringBuilder sb = new StringBuilder();
      for (byte b: encrypted) {
        sb.append((char)b);
      }

      // the encrypted String
      String enc = sb.toString();
      return enc;

      // now convert the string to byte array
      // for decryption

    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return "";
  }

  public String decrypt(String enc) {
    String key = "Bar12345Bar12345";
    Key aesKey = new SecretKeySpec(key.getBytes(), "AES");

    try {
      Cipher cipher = Cipher.getInstance("AES");
      cipher.init(Cipher.DECRYPT_MODE, aesKey);
      byte[] bb = new byte[enc.length()];
      for (int i = 0; i < enc.length(); i++) {
        bb[i] = (byte) enc.charAt(i);
      }

      // decrypt the text
      cipher.init(Cipher.DECRYPT_MODE, aesKey);
      String decrypted = new String(cipher.doFinal(bb));
      return decrypted;
    } catch (Exception e) {
      System.out.println(":(");
    }
    return "";
  }

  byte[] parseHex(String str) {
    byte[] a = new BigInteger(str, 16).toByteArray();
    if (a.length != str.length() / 2) {
      a = Arrays.copyOfRange(a, 1, a.length);
    }
    return a;
  }
}

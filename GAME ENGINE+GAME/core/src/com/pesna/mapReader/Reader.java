package com.pesna.mapReader;

import com.pesna.Main;
import com.pesna.objects.LevelRenderer;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.io.*;
import java.math.BigInteger;
import java.security.Key;
import java.util.Arrays;


import static java.lang.System.out;

/**
 * Created by Gagiu Filip on 7/24/2017.
 */
public class Reader
{
    private final Main reference;
    public String[] DATA = new String[50];
    private StringBuilder builder = new StringBuilder();
    public Reader(Main ref)
    {
        reference = ref;
    }
    private Uncryptor uncryptor = new Uncryptor();
    public void ReadEncrypt(){
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            getMetaData(file);
        }
    }

    private int i = 0;
    String temp1, temp2;
    private void getMetaData(File file)
    {
        BufferedReader reader;
        String line;
        System.out.println("---------------------------------");
        try {
            try (InputStream in = new FileInputStream(file)) {
                reader = new BufferedReader(new InputStreamReader(in, "UTF-32"));
                line = reader.readLine();
                while (line != null) {
                    DATA[i]= uncryptor.decrypt(line);
                    i++;
                    line = reader.readLine();
                }
                reader.close();
            }
        } catch (IOException e) {
            //Throw Empty
        }
        finally {
            for(int J = 0 ; J < i;J++) {
                System.out.println(DATA[J]);
                if (DATA[J].contains("background"))
                {
                    System.out.println("uu");
                    temp1 = DATA[J].replace("background ", "");
                }
                if (DATA[J].contains("platform"))
                {
                    temp2 = DATA[J].replace("platform ", "");
                }
            }
            if(temp1!=null && temp2 != null)
            reference.gameRegistry.levelManager.getTextures(temp1,temp2);
        }
        System.out.println(DATA);
    }
}

final class Uncryptor
{
    String decrypt(String enc) {
        String key = "Bar12345Bar12345";
        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            byte[] bb = new byte[enc.length()];
            for (int i = 0; i < enc.length(); i++) {
                bb[i] = (byte) enc.charAt(i);
            }
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            return new String(cipher.doFinal(bb));
        } catch (Exception e) {
            out.println(":(");
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
package com.mygdx.game.infoManager;

import com.mygdx.game.editorCore.Encryptor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Gagiu on 4/23/2017.
 */


public class reader {

  private Encryptor encryptor = new Encryptor();

    public void read()
    {
      File file = new File("1.psn");
      BufferedReader reader = null;
      String line = "";
      try {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-32"));
       line = reader.readLine();
        while (line != null) {
          System.out.println( encryptor.decrypt(line));
          line = reader.readLine();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }

    }
}

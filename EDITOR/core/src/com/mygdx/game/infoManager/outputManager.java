package com.mygdx.game.infoManager;

import com.mygdx.game.editorCore.Encryptor;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Gagiu on 4/22/2017.
 */
public class outputManager {

    private String MetaData = "";
    private String Name = "1";
    private Encryptor encryptor = new Encryptor();

    public void putData(String newData) {
      System.out.println("_________________________");
      System.out.println(newData);
      System.out.println(encryptor.secure(newData));
      MetaData += encryptor.secure(newData) + "\n";
      System.out.println(MetaData);
      System.out.println("_________________________");
    }

    public void SaveData(){
      CreateFile();
    }

    private void CreateFile()
    {
      try{
        PrintWriter writer = new PrintWriter(Name + ".psn", "UTF-32");
        writer.println(MetaData);
        writer.close();
      } catch (IOException e) {
        // do something
      }
    }

}

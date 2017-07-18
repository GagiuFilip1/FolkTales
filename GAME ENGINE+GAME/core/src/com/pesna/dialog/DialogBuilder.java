package com.pesna.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.pesna.Main;
import com.pesna.objects.ScreenObject;
import com.pesna.objects.SimpleTextBox;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Gagiu on 1/7/2017.
 */
public class DialogBuilder implements ScreenObject
{
  private Main reference;
  private SimpleTextBox dialogTextBox;
  private StringTokenizer dialogReader;
  private final String file;
  private String Person1 , Person2 ,newWord1 ="" , newWord2="";
  private List<String> Person1Lines , Person2Lines, FinalDialog;
  private boolean AddFirst , AddSecond , DRAW = false , ADDED;
  private String lines;
  private Texture texture;

  public DialogBuilder(Main _reference)
  {
    reference = _reference;
    texture = new Texture(Gdx.files.internal("itemsSprites/dialogBack.png"));
    dialogTextBox = new SimpleTextBox(reference,460, 180);
    file = "dialog/DialogText";
    Person1Lines = new LinkedList<String>();
    Person2Lines = new LinkedList<String>();
    FinalDialog = new LinkedList<String>();
    dialogTextBox.SetTexture(texture);
    dialogTextBox.SetTextColor(Color.FIREBRICK);
  }

  public void SetDialog(String Person_1 , String Person_2) throws IOException {
    Person1 = Person_1;
    Person2 = Person_2;
    GetFile();
    GetDialog();
  }

  private void GetFile()
  {
  File file = new File("dialog/DialogText");
  try {
    FileReader reader = new FileReader(file);
    BufferedReader buffReader = new BufferedReader(reader);
    int x = 0;
    String s;
    while((s = buffReader.readLine()) != null){
      lines += s + " ";
      x++;
    }
  }
  catch(IOException e){
    //handle exception
  }
  }
int z = 1;
  private void GetDialog()
  {
    dialogReader = new StringTokenizer(lines);
    while (dialogReader.hasMoreTokens()) {
      String curentWord = "";
      curentWord = dialogReader.nextToken();
      if (curentWord != null) {

        if (curentWord.equals("*"))
        {
          if (AddFirst) {
              Person1Lines.add(newWord1 + " ");
              newWord1 = "";
            // System.out.println("Gasit " + newWord1 + " ||");
          }
          if (AddSecond) {


            Person2Lines.add(newWord2 + " ");
            newWord2 = "";
            // System.out.println("Gasit2" + newWord2);
          }
          AddFirst = false;
          AddSecond = false;
        }

        if (curentWord.equals(Person1)) {
          AddFirst = true;
          curentWord = dialogReader.nextToken();
        } else if (curentWord.equals(Person2))
        {
          AddSecond = true;
          z++;
          curentWord = dialogReader.nextToken();
        }
        if (AddFirst)
        {
          AddSecond = false;
          if(curentWord.equals(".n"))
          {
            curentWord = "\n";
          }
          newWord1 += curentWord + " ";
        }
        if (AddSecond)
        {
          AddFirst = false;
          if(curentWord.equals(".n"))
          {
            curentWord = "\n";
          }
          newWord2 += curentWord + " ";
        }
      }
      //System.out.println(z);
    }
    Finalize();
  }


  int index1 = 0 , index2 = 0;
  private void Finalize()
  {
    for(int i = 0 ; i <= Person1Lines.size() + Person2Lines.size();i++)
    {
      try {

        if (i % 2 == 0)
        {
          if(Person1Lines.get(index1) !=null) {

            FinalDialog.add(Person1Lines.get(index1) + " ");
          }
          index1++;
        }
        else
          {
          if(Person2Lines.get(index2) !=null) {

            FinalDialog.add(Person2Lines.get(index2));
          }
          index2++;
        }
      }catch (IndexOutOfBoundsException ex)
      {

      }
    }
  }

  int personindex= 0;
  @Override
  public void draw(Main _reference)
  {
   // System.out.println(AddSecond);
    dialogTextBox.SetPosition((int)reference.camera.position.x-650 , (int)reference.camera.position.y - 330);
    if(DRAW) {
      try {
        if (personindex % 2 == 0) {
          dialogTextBox.Clear();
          if (FinalDialog.get(personindex) != null) {
            dialogTextBox.Write("\n"+"\n"+Person1 + "\n" + FinalDialog.get(personindex) + " ");
          }
        } else {
          dialogTextBox.Clear();
          dialogTextBox.Write("\n"+"\n"+Person2 + "\n" + FinalDialog.get(personindex));
        }
        if (Gdx.input.isKeyJustPressed(Keys.L)) {
          personindex++;
        }
      }catch(IndexOutOfBoundsException ex)
      {
        System.out.println("wow");
        DRAW = false;
        dialogTextBox.Destroy();
        personindex = 0;
      }
    }
  }

    public void Start()
    {
      if(Gdx.input.isKeyJustPressed(Input.Keys.F)) {
        if (!ADDED) {
          ADDED = true;
          reference.screenManager.gameScreen.ObjectForceAdd(this);
          reference.screenManager.gameScreen.ObjectForceAdd(dialogTextBox);
          DRAW = true;
        }
          dialogTextBox.DRAW = true;

      }
    }
  @Override
  public void update(Main _reference)
  {
    System.out.println(DRAW);
    if(Gdx.input.isKeyJustPressed(Input.Keys.F))
    {
      DRAW = true;
    }
  }
}

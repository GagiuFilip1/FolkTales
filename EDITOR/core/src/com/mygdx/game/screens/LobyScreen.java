package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.Main;
import com.mygdx.game.abstracts.IScreen;
import com.mygdx.game.utils.SimpleButton;
import com.mygdx.game.utils.TextBox;

/**
 * Created by Gagiu Filip on 7/15/2017.
 */
public class LobyScreen extends IScreen
{

    private final Main reference;
    private TextBox textBox;
    private boolean usernameCheck = false;
    private BitmapFont Username = new BitmapFont() , ID = new BitmapFont();
    private boolean connected = false;
    public LobyScreen(Main ref)
    {
        reference = ref;
        textBox = new TextBox(ref);

        textBox.SetBounds(200,50);
        textBox.SetPosition(600,600);
        textBox.SetCharLimit(20);
    }

    private String hash = "";
    @Override
    public void draw()
    {
        if(!usernameCheck) {
            textBox.Render();
            textBox.Update();
            if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
            {
                reference.colaborationModule.onlineBuilder.myUsername = textBox.textData;
                usernameCheck = true;
            }
        }
        else
        {
            if(!connected) {
                reference.colaborationModule.onlineBuilder.start();
                reference.colaborationModule.Receive();
            connected = true;
            }
            reference.batch.begin();
            if(reference.colaborationModule.onlineBuilder.GetID()!=null)
            {
                hash = reference.colaborationModule.onlineBuilder.GetID().substring(0,4);
                Username.draw(reference.batch,"YOUR USERNAME : " + reference.colaborationModule.onlineBuilder.myUsername.replace('\n',' ') +"#" + hash, 10,400);
            }
            reference.batch.end();
        }
    }

    @Override
    public void destroy()
    {

    }
}

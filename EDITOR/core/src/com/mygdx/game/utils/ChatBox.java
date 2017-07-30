package com.mygdx.game.utils;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Main;
import com.mygdx.game.abstracts.screenObject;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by Gagiu Filip on 7/26/2017.
 */
public class ChatBox extends screenObject
{

    private final Main ref;
    private ArrayList<String> conversation;
    private BitmapFont writer;
    float x,y;
    private float WIDTH , HEIGHT, LINE_DOWN, SPACE = 10;

    public ChatBox(Main _ref)
    {
        ref = _ref;
        conversation = new ArrayList<>();
        writer = new BitmapFont();
    }

    public void ApendText(String s)
    {
        conversation.add(s);
    }

    public String CreateText(String Username, String reply)
    {
        return Username + ": " + reply;
    }


    @Override
    public void Render()
    {
        ref.batch.begin();
        for(String reply : conversation)
        {
            if(conversation.indexOf(reply) >= conversation.size() - 6) {
                LINE_DOWN = HEIGHT - (SPACE * conversation.indexOf(reply) - SPACE);
                writer.draw(ref.batch, reply, 0, HEIGHT - LINE_DOWN);
            }
        }
    }

    @Override
    public void Update() {

    }

    @Override
    public void Destroy()
    {

    }

    private void buildBody()
    {

    }
}

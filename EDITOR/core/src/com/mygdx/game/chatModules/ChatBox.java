package com.mygdx.game.chatModules;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.Main;
import com.mygdx.game.abstracts.screenObject;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by Gagiu Filip on 7/30/2017.
 */
public class ChatBox extends screenObject
{

    private LinkedList<String> chatContainer;
    private final Main reference;
    private final BitmapFont writer;
    private final float MESSAGE_DISTANCE = 20;

    public ChatBox(Main ref)
    {
        chatContainer = new LinkedList<>();
        reference = ref;
        writer = new BitmapFont();
    }

    @Override
    public void Render() {
        reference.batch.begin();
        for(String s :chatContainer)
        {
            if(chatContainer.size() - chatContainer.indexOf(s) <= 12)
                writer.draw(reference.batch , s,20 ,30 + (MESSAGE_DISTANCE * (chatContainer.size() - chatContainer.indexOf(s))));
        }
        reference.batch.end();
    }

    @Override
    public void Update() {

    }

    @Override
    public void Destroy() {

    }

    public void AddReply(String Sender , String Message)
    {
        chatContainer.add(Sender + " : " + Message);
    }
}

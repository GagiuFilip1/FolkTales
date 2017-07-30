package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.Main;
import com.mygdx.game.abstracts.IScreen;
import com.mygdx.game.utils.SimpleButton;
import com.mygdx.game.utils.TextBox;
import com.sun.org.apache.xerces.internal.util.TeeXMLDocumentFilterImpl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Gagiu Filip on 7/15/2017.
 */
public class LobyScreen extends IScreen
{

    private final Main reference;
    private TextBox userBox , passwordBox;
    private LinkedList<String> friendList = new LinkedList<>();

    private boolean usernameCheck = false, createdMultiChat = false;
    private BitmapFont friendDrawer = new BitmapFont() , ID = new BitmapFont();
    private boolean connected = false;
    private SimpleButton start, addF, invite;
    private TextBox textBox , roomBox;

    public LobyScreen(Main ref)
    {
        reference = ref;
        userBox = new TextBox(ref);
        passwordBox = new TextBox(ref);
        roomBox = new TextBox(ref);
        invite = new SimpleButton(ref);
        userBox.SetBounds(200,50);
        userBox.SetPosition(600,600);
        userBox.SetCharLimit(20);

        passwordBox.SetBounds(200,50);
        passwordBox.SetPosition(600,400);
        passwordBox.SetCharLimit(20);
        start = new SimpleButton(ref);
        addF = new SimpleButton(ref);
        textBox = new TextBox(ref);
        addF.setPosition(620,500);
        addF.setBounds(100,50);
        start.setBounds(100,50);
        roomBox.SetCharLimit(20);
        roomBox.SetBounds(300,30);
        roomBox.SetPosition(300,100);
        textBox.SetBounds(300,30);
        textBox.SetPosition(300,500);
        textBox.SetCharLimit(20);
        start.setPosition(500,300);
        invite.setBounds(100,50);
        invite.setPosition(650, 100);
    }

    private String hash = "";
    @Override
    public void draw()
    {
        if(!usernameCheck) {
            userBox.Render();
            userBox.Update();
            passwordBox.Render();
            passwordBox.Update();

            if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
            {
                reference.colaborationModule.onlineBuilder.SetCredentials("e3","1" , true);
                usernameCheck = true;
                reference.IS_LOGGED = true;
            }
        }

        else
        {
             reference.IS_LOGGED = true;
             reference.colaborationModule.Receive();
            int x = 0;
            for (Object o : reference.colaborationModule.onlineBuilder._friendList.entrySet()) {
                HashMap.Entry pair = (HashMap.Entry) o;
                reference.batch.begin();
                friendDrawer.draw(reference.batch, pair.getKey() + " / " + pair.getValue(), 500, 600 - 20 * x);
                reference.batch.end();
            }
            textBox.Render();
            textBox.Update();
            addF.Render();
            addF.Update();
            start.Render();
            start.Update();
            roomBox.Render();
            roomBox.Update();
            invite.Render();
            invite.Update();
            if(start.IsClicked())
            {
                reference.screenManager.setScreen(new EditorScreen(reference));
            }
            if(addF.IsClicked())
            {
                reference.colaborationModule.onlineBuilder.add_toRoster(textBox.textData);
            }
            if(!createdMultiChat) {
                reference.colaborationModule.onlineBuilder.create_multiChat();
                reference.colaborationModule.onlineBuilder.invitationListener();
                createdMultiChat = true;
            }

            if(invite.IsClicked())
            {
                System.out.println(roomBox.textData);
                if(!roomBox.textData.equals(""))
                reference.colaborationModule.onlineBuilder.inviteToMUC(roomBox.textData , reference.colaborationModule.onlineBuilder.socketBuilder.room);
                roomBox.textData = "";
            }
        }
    }

    @Override
    public void destroy()
    {

    }
}

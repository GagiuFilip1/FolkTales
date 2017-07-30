package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.Main;
import com.mygdx.game.abstracts.IScreen;
import com.mygdx.game.chatModules.ChatBox;
import com.mygdx.game.utils.SimpleButton;
import com.mygdx.game.utils.TextBox;

import java.security.Key;

/**
 * Created by Gagiu Filip on 7/15/2017.
 */
public class EditorScreen extends IScreen
{

    private final Main reference;
    private TextBox textBox;
    ChatBox chatBox;
    public EditorScreen(Main ref) {
        reference = ref;
        chatBox = new ChatBox(ref);
        textBox = new TextBox(ref);
        textBox.SetCharLimit(200);
        textBox.SetPosition(20,0);
        textBox.SetBounds(300, 30);
    }

    @Override
    public void draw() {
        reference.editorGUI.Render();
        reference.editorGUI.Update();
        reference.editor.startRuntime();
        reference.infoViewer.Render();
        reference.infoViewer.Update();
        if(reference.IS_LOGGED)
        {
            chatBox.Render();
            chatBox.Update();
            textBox.Update();
            textBox.Render();
            if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
            {
                chatBox.AddReply(reference.colaborationModule.onlineBuilder._myUsername, textBox.textData);
                textBox.Clear();
            }
        }

    }

    @Override
    public void destroy() {
        reference.editorGUI.Destroy();
        reference.infoViewer.Destroy();
    }
}

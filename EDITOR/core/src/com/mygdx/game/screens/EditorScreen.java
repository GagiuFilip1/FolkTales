package com.mygdx.game.screens;

import com.mygdx.game.Main;
import com.mygdx.game.abstracts.IScreen;
import com.mygdx.game.utils.SimpleButton;

/**
 * Created by Gagiu Filip on 7/15/2017.
 */
public class EditorScreen extends IScreen
{

    private final Main reference;

    public EditorScreen(Main ref) {
        reference = ref;
        System.out.println("editor screen");
    }

    @Override
    public void draw() {
        reference.editorGUI.Render();
        reference.editorGUI.Update();
        reference.editor.startRuntime();
        reference.infoViewer.Render();
        reference.infoViewer.Update();
    }

    @Override
    public void destroy() {
        reference.editorGUI.Destroy();
        reference.infoViewer.Destroy();
    }
}

package com.mygdx.game.screens;

import com.mygdx.game.Main;
import com.mygdx.game.abstracts.IScreen;
import com.mygdx.game.utils.SimpleButton;
/**
 * Created by Gagiu Filip on 7/15/2017.
 */
public class MenuScreen extends IScreen
{

    private SimpleButton singleEdit , multiEdit;
    private final Main reference;
    public MenuScreen(Main ref)
    {
        reference = ref;
        singleEdit = new SimpleButton(reference);
        multiEdit = new SimpleButton(reference);
        singleEdit.setBounds(300,50);
        singleEdit.addText("Start Editing Alone");
        singleEdit.setPosition(100, 200);
        multiEdit.setBounds(300,50);
        multiEdit.addText("Start A Team");
        multiEdit.setPosition(600, 200);
    }
    @Override
    public void draw()
    {
        singleEdit.Render();singleEdit.Update();multiEdit.Render();multiEdit.Update();
        if(singleEdit.IsClicked()) reference.screenManager.setScreen(new EditorScreen(reference));
        if(multiEdit.IsClicked()) reference.screenManager.setScreen(new LobyScreen(reference));
    }

    @Override
    public void destroy() {

    }
}

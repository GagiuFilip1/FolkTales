package com.mygdx.game.screens;

import com.mygdx.game.abstracts.IScreen;

/**
 * Created by Gagiu Filip on 7/15/2017.
 */
public class ScreenManager
{
    private IScreen curentScreen;

    public void setScreen(IScreen screen)
    {
        curentScreen = screen;
    }

    public void updateScreen()
    {
        curentScreen.draw(); curentScreen.destroy();
    }
}

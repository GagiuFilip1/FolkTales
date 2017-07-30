package com.pesna.screens;

import com.pesna.Main;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Gagiu Filip on 7/29/2017.
 */
public class GameScreenTest {

    @Test
    public void onAssetsLoaded() throws Exception {
        Main main = new Main();
        GameScreen testgameScreen = new GameScreen(main);
        assertTrue(testgameScreen.FullLoaded);
    }

    @Test
    public void update() throws Exception {
        Main main = new Main();
        GameScreen testgameScreen = new GameScreen(main);
        assertTrue(testgameScreen.Updating);
    }

    @Test
    public void draw() throws Exception {
        Main main = new Main();
        GameScreen testgameScreen = new GameScreen(main);
        assertTrue(testgameScreen.Drawing);
    }

}
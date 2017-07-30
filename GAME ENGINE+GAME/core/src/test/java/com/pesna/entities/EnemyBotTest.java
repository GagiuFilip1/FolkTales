package com.pesna.entities;

import com.pesna.Main;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Gagiu Filip on 7/29/2017.
 */
public class EnemyBotTest {
    @Test
    public void update() throws Exception {
        Main main = new Main();
        EnemyBot bot = new EnemyBot(main,0,0,0,0);
        assertTrue(bot.UPDATING);
    }

    @Test
    public void draw() throws Exception {
        Main main = new Main();
        EnemyBot bot = new EnemyBot(main,0,0,0,0);
        assertTrue(bot.DRAWING);
    }

    @Test
    public void setAnimation() throws Exception {
        Main main = new Main();
        EnemyBot bot = new EnemyBot(main,0,0,0,0);
        assertTrue(bot.ANIMATIONS_WORKING);
    }

}
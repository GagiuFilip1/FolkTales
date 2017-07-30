package com.pesna.entities;

import com.pesna.Main;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Gagiu Filip on 7/30/2017.
 */
public class BearTest {
    @Test
    public void setAnimation() throws Exception {
        Main main = new Main();
        Bear bear = new Bear(main,0,0);
        assertTrue(bear.SETING_ANIMATION);
    }

    @Test
    public void draw() throws Exception {
        Main main = new Main();
        Bear bear = new Bear(main,0,0);
        assertTrue(bear.DRAWING);
    }

    @Test
    public void update() throws Exception {
        Main main = new Main();
        Bear bear = new Bear(main,0,0);
        assertTrue(bear.UPDATING);
    }
}
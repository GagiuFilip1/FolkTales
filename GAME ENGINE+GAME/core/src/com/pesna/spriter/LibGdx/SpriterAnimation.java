package com.pesna.spriter.LibGdx;

/**
 * Created by Zenibryum on 4/6/2017.
 */
/**Holds information regarding the playback of the animations via Spriter*/
public class SpriterAnimation {
    /*
    //Pesna Animations
    run    ( 1, 0, 0, 115, true, 0.2f ),
    jump   ( 1, 0, 1, 115, true, 0.2f ),
    death  ( 1, 1, 0, 115, true, 0.2f ),
    jumps  ( 1, 1, 1, 115, true, 0.2f ),
    attack ( 1, 2, 0, 115, true, 0.2f ),
    idle   ( 1, 0, 0,145, false,0.16f),
    archery( 1, 0, 1,145, false,0.16f),

    //Bear Animations
    bear_run( 1, 0, 0, 160, true,0.5f ),
    bear_attack_up( 1, 1, 0, 0, true, 0.5f ),
    bear_attack( 1, 2, 0, 0, true, 0.5f );
    */
    //Lucifer

    public static final SpriterAnimation run = new SpriterAnimation( 1, 0, 0, 115, true, 0.2f );
    public static final SpriterAnimation idle = new SpriterAnimation( 1, 0, 0, 145, false, 0.16f );
    public static final SpriterAnimation attack = new SpriterAnimation( 1, 2, 0, 115, true, 0.2f );

    public final int animationSpeed, entityID, animationID, yOffset;
    public final boolean mainLoader;
    public final float projectionScale;

    public SpriterAnimation(int animationSpeed, int entityID, int animationID, int yOffset, boolean mainLoader, float projectionScale ) {
        this.animationSpeed = animationSpeed;
        this.entityID = entityID;
        this.animationID = animationID;
        this.yOffset = yOffset;
        this.mainLoader = mainLoader;
        this.projectionScale = projectionScale;
    }
}
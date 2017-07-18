package com.pesna.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.pesna.Main;
import com.pesna.objects.ScreenObject;
import com.pesna.objects.SimpleLabel;
import com.pesna.player.Player;
import com.pesna.spriter.LibGdx.SpriterAnimation;
import com.pesna.spriter.LibGdx.LibGdxDrawer;
import com.pesna.spriter.LibGdx.SpriterAnimationBundle;

/**
 * Created by Zenibryum on 4/4/2017.
 */
public class EnemyBot implements ScreenObject
{
    private Main reference;

    public float ATTACK_SPEED,SPEED;
    public float x ,y;
    public int HP, CHASE_RANGE, ATTACK_RANGE;

    public Player target;

    private SimpleLabel hpLabel ,nameLabel;
    boolean flip;

    public com.brashmonkey.spriter.Data spriterData;
    public com.brashmonkey.spriter.Player spriterPlayer;
    public LibGdxDrawer spriterDrawer;
    public SpriterAnimation spriterAnimation;

    public SpriterAnimation runAnimation, attackAnimation, idleAnimation;

    public EnemyBot( Main _reference, int posX , int posY, int GraphicsID, int BehaviourID )
    {
        hpLabel = new SimpleLabel(_reference);
        hpLabel.SetColor(Color.YELLOW);
        nameLabel = new SimpleLabel(_reference);
        nameLabel.SetColor(Color.YELLOW);
        reference = _reference;

        x = posX;
        y = posY;

        BehaviourBundle behaviourBundle = reference.gameRegistry.behaviourBundles[BehaviourID];

        HP = behaviourBundle.HP;
        ATTACK_SPEED = behaviourBundle.ATTACK_SPEED;
        SPEED = behaviourBundle.SPEED;
        CHASE_RANGE = behaviourBundle.CHASE_RANGE;
        ATTACK_RANGE = behaviourBundle.ATTACK_RANGE;

        SpriterAnimationBundle animationBundles = reference.gameRegistry.animationBundles[GraphicsID];

        runAnimation = animationBundles.run;
        idleAnimation = animationBundles.idle;
        attackAnimation = animationBundles.attack;


        target = reference.player;

        spriterData = reference.gameRegistry.spriterData[GraphicsID];
        spriterPlayer = new com.brashmonkey.spriter.Player( spriterData.getEntity(0) );
        spriterDrawer = reference.gameRegistry.spriterDrawer[GraphicsID];

        //setAnimation(SpriterAnimation.bear_run);

        setAnimation ( idleAnimation );

        //entity 0 running
        //entity 1 attack_upwards
        //entity 2 attack_normal
        //spriterPlayer.setEntity( reference.gameRegistry.werewolfData.getEntity(0 ) );
        //spriterPlayer.speed=3;
    }

    public void update(Main main)
    {
        //This is the main behavioural routine
        if ( distanceToTarget() <= ATTACK_RANGE )
            attack();
        else if ( distanceToTarget() <= CHASE_RANGE )
            run();
        else
            idle();
    }
 
    public void draw(Main _reference) {
        if (!IS_Dead()) {
            drawHealth();

            float cameraScale = spriterAnimation.projectionScale;
            float reverseScale = 1f/cameraScale;

            spriterPlayer.setPosition(x*reverseScale,(y+spriterAnimation.yOffset)*reverseScale);
            spriterPlayer.update();

            if ( spriterPlayer.flippedX() == 1 && flip == true )
                spriterPlayer.flipX();
            if ( spriterPlayer.flippedX() == -1 && flip == false )
                spriterPlayer.flipX();

            Matrix4 cameracombined = _reference.camera.combined;
            _reference.batch.setProjectionMatrix( cameracombined.scale(cameraScale,cameraScale,1f) );
            _reference.batch.begin();
            spriterDrawer.draw(spriterPlayer);
            _reference.batch.end();
            _reference.batch.setProjectionMatrix( cameracombined.scale(reverseScale,reverseScale,1f) );

            drawHealth();
        }
    }

    public void setAnimation( SpriterAnimation animation )
    {
        this.spriterAnimation = animation;
        spriterPlayer.setEntity( spriterData.getEntity(animation.entityID));

        com.brashmonkey.spriter.Animation newAnimation = spriterPlayer.getEntity().getAnimation( animation.animationID );

        if ( newAnimation != spriterPlayer.getAnimation() ) {
            //spriterPlayer.setAnimation(spriterPlayer.getEntity().getAnimation(animation.animationID));
            spriterPlayer.setTime( 0 );
        }
        //else
        //spriterPlayer.setAnimation(spriterPlayer.getEntity().getAnimation(animation.animationID));

        spriterPlayer.speed = animation.animationSpeed;
    }

    /**Sets the EnemyBot on the attacking routine*/
    public void attack()
    {
        flipAccordingly();
        setAnimation(attackAnimation);
        if ( spriterPlayer.getTime() == spriterPlayer.getAnimation().length )
            dealDamage();
    }

    /**Sets the EnemyBot on the chasing routine*/
    public void run()
    {
        flipAccordingly();
        setAnimation(runAnimation);
        if ( x < target.x )
        {
            x += Gdx.graphics.getDeltaTime() * SPEED;
        }
        else
            x -= Gdx.graphics.getDeltaTime() * SPEED;
    }

    /**Sets the EnemyBot on the sitting routine*/
    public void idle()
    {
        flipAccordingly();
        setAnimation(idleAnimation);
    }

    public void dealDamage()
    {
        System.out.println("EnemyBot hit the player");
    }

    public void flipAccordingly()
    {
        if ( target.x < x != ( spriterPlayer.flippedX() == 1 ) ) // if the target is to the left of the EnemyBot
        {
            spriterPlayer.flipX();
        }
    }

    private boolean expAdded = false;
    public boolean IS_Dead()
    {
        if(HP <= 0)
        {
            if(!expAdded) {
                reference.player.Experience += 300;
                expAdded = true;
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    private void drawHealth()
    {
        float procent;
        procent = (HP * 140)/ 10000;
        reference.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        reference.shapeRenderer.setColor(Color.FIREBRICK);
        reference.shapeRenderer.rect(x - 40, y +400,procent,20);
        reference.shapeRenderer.end();
        hpLabel.SetPosition(x + 15 , y+418);
        hpLabel.Draw(String.valueOf(HP));
        nameLabel.SetPosition(x + 15, y + 460);
        nameLabel.Draw("Urs");
    }

    public int distanceToTarget()
    {
        return Math.abs( (int)( target.x - x ) );
    }
}
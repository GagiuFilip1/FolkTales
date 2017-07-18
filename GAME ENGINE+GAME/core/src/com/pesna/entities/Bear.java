package com.pesna.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.pesna.Main;
import com.pesna.abstracts.EnemyStructure;
import com.pesna.objects.SimpleLabel;
import com.pesna.objects.SimpleTimer;
import com.pesna.objects.SoundManager;
import com.pesna.player.Player;
import com.pesna.spriter.LibGdx.SpriterAnimation;

/**
 * Created by Zenibryum on 4/4/2017.
 */
public class Bear extends EnemyStructure
{
    private Main reference;
    private SimpleTimer bleedTimer , slowTimer;

    boolean flip;
    private SimpleTimer atackTimer , AuxiliarTimer;
    private AITactics thisLogic;
    public Animation animation;
    public float ATTACK_SPEED,SPEED;
    public float x ,y , auxX;
    private float delta = 0.0f , FollowRange = 800, AttackRange = 100, HP ,ATTACK_DAMANGE;
    private boolean pause = false;
    private float counter = 0;
    private SimpleLabel hpLabel ,nameLabel;
    public boolean IsBleeding = false , IsRunning = false , isHited = false;
    private SoundManager runSound , attackSound;

    public com.brashmonkey.spriter.Player spriterPlayer;

    public SpriterAnimation spriterAnimation;

    public Bear(Main _reference, int posX , int posY)
    {
        hpLabel = new SimpleLabel(_reference);
        hpLabel.SetColor(Color.YELLOW);
        nameLabel = new SimpleLabel(_reference);
        nameLabel.SetColor(Color.YELLOW);
        runSound = new SoundManager("sounds/walk.mp3");
        runSound.LoopSound(true);
        bleedTimer = new SimpleTimer(2.0f);
        reference = _reference;
        x = posX;
        slowTimer = new SimpleTimer(7.0f);
        y = posY;
        HP = 10000;
        ATTACK_DAMANGE = 10;
        ATTACK_SPEED = enemyStats.maxAttack_Speed;
        SPEED = enemyStats.maxSpeed;
        thisLogic = new AITactics(reference);
        animation = reference.gameRegistry.animationManager.eidle;
        atackTimer = new SimpleTimer(ATTACK_SPEED);
        AuxiliarTimer = new SimpleTimer(1.0f);
//        attackSound.PLay();

        spriterPlayer = new com.brashmonkey.spriter.Player( reference.gameRegistry.werewolfData.getEntity(0) );

        //setAnimation(SpriterAnimation.bear_run);

        //entity 0 running
        //entity 1 attack_upwards
        //entity 2 attack_normal
        //spriterPlayer.setEntity( reference.gameRegistry.werewolfData.getEntity(0 ) );
        //spriterPlayer.speed=3;
    }

    public void setAnimation( SpriterAnimation animation )
    {
        this.spriterAnimation = animation;
        spriterPlayer.setEntity(reference.gameRegistry.werewolfData.getEntity(animation.entityID));

        com.brashmonkey.spriter.Animation newAnimation = spriterPlayer.getEntity().getAnimation( animation.animationID );

        if ( newAnimation != spriterPlayer.getAnimation() ) {
            //spriterPlayer.setAnimation(spriterPlayer.getEntity().getAnimation(animation.animationID));
            spriterPlayer.setTime( 0 );
        }
        //else
            //spriterPlayer.setAnimation(spriterPlayer.getEntity().getAnimation(animation.animationID));

        spriterPlayer.speed = animation.animationSpeed;
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
            _reference.werewolfDrawer.draw(spriterPlayer);
            _reference.batch.end();
            _reference.batch.setProjectionMatrix( cameracombined.scale(reverseScale,reverseScale,1f) );
        }
    }
    public void update(Main main)
    {
        if(!IS_Dead()) {
            /*
            if(animation == reference.gameRegistry.animationManager.eidle)
            {
                if ( y > - 30)
                {
                    y -= 30;
                }
            }
            else
            {
                if( y < 0)
                {
                    y = 0;
                }
            }
            */
            thisLogic.SetOffensive(1, this);
            WasAttacked();
            slowed();
        }
    }
    @Override
    public int GetPosition(int _x) {
        switch (_x) {
            case 1:
                return (int)x;
            case 2:
                return (int)y;
            default:
                return 0;
        }
    }
    private boolean playattack = false;
    @Override
    public void Attack(Player player)
    {
        runSound.Pause();
        IsRunning = false;
        if(IS_IN_RANGE(player, AttackRange) && !pause)
        {
            player.isEnemyClose = true;
            if(atackTimer.TimeElapsed())
            {

                animation = reference.gameRegistry.animationManager.eattack;
                playattack = true;
                player.TakeDamage( (int) ATTACK_DAMANGE );
                reference.player.isAttacked = true;
            }
            else if( animation == reference.gameRegistry.animationManager.eattack)
            {
                if(AuxiliarTimer.TimeElapsed())
                {
                    animation = reference.gameRegistry.animationManager.eidle;
                }
            }
            else
            {
                animation = reference.gameRegistry.animationManager.eidle;
            }

            reference.player.isAttacked = false;
            flip = !(x - player.x >= 0);
        }
        else
        {
            player.isEnemyClose = false;
        }
    }

    @Override
    public void Follow(Player player)
    {
        if(IS_IN_RANGE(player, FollowRange) && !pause && !IS_IN_RANGE(player, AttackRange) && !IS_IN_RANGE(player , 30))
        {
            if( player.x - x > 10 )
            {
                x += SPEED * Gdx.graphics.getDeltaTime();
                auxX = x;
            }
            else if(player.x - x < -10)
            {

                x -= SPEED * Gdx.graphics.getDeltaTime();
                auxX = x;
            }
            flip = !(x - player.x >= 0);
            animation = reference.gameRegistry.animationManager.erun;
        }
        else if (pause)
        {
            fall(player);
        }
        else
        {
            if(!IS_IN_RANGE(player , 100))
                animation = reference.gameRegistry.animationManager.eidle;
        }
    }

    @Override
    public void Run(Player player)
    {
        if(IS_IN_RANGE(player, FollowRange+150) && !pause && !IS_IN_RANGE(player , 100))
        {
            if(player.x - x > 10)
            {
                x -= SPEED * Gdx.graphics.getDeltaTime();
                auxX = x;
            }
            else if(player.x - x < -10)
            {
                x += SPEED * Gdx.graphics.getDeltaTime();
                auxX = x;
            }
            flip = !(x - player.x <= 0);
            animation = reference.gameRegistry.animationManager.erun;
        }
        else if (pause)
        {
            fall(player);
        }
    }

    @Override
    public float GetAtkSpeed() {
        return ATTACK_SPEED;
    }

    public boolean IS_IN_RANGE(Player player, float range)
    {
        return Math.abs(Math.abs(player.x) - x) <= range;
    }

    private boolean Timer(int time)
    {
        counter += Gdx.graphics.getDeltaTime();
        return counter > time;
    }
    public void TakeDamange(Player player)
    {
        if(IS_IN_RANGE(player, AttackRange) && player.autoattackTimer.TimeElapsed() && Gdx.input.isKeyPressed(
                Input.Keys.Q) )
        {
            HP -= player.DAMAGE;
        }
    }
    @Override
    public void fall(Player player)
    {
        TakeDamange(player);
        if(pause)
        {
            if(player.x - x > 0)
            {
                auxX -= 40 * Gdx.graphics.getDeltaTime();
                if(Math.abs(auxX - x) > 100)
                {
                    pause = false;
                    x = auxX;
                }
            }
            else if(player.x - x <0)
            {
                auxX += 100 * Gdx.graphics.getDeltaTime();
                if(Math.abs(auxX - x) > 100)
                {
                    pause = false;
                    x = auxX;
                }
            }
            animation = reference.gameRegistry.animationManager.fall;
        }
    }

    @Override
    public void SetAnimation(Animation _animation)
    {
        animation = _animation;
    }

    @Override
    public void SetBuff(String statName, float AddValue)
    {
        if(statName.equals("Speed"))
        {
            SPEED += AddValue;
        }
        else if(statName.equals("Attack"))
        {
            ATTACK_DAMANGE += AddValue;
        }
        else if(statName.equals("AttackSpeed"))
        {
            ATTACK_SPEED += AddValue;
        }
    }

    public void TakeDamage(float value)
    {
        HP -= value;
        isHited = true;
    }

    public void DealDamage(Player player)
    {

    }

    private boolean IS_ATTACKED()
    {
        return reference.player.animation == reference.gameRegistry.animationManager.hattack;
    }

    private boolean aux;
    private void slowed()
    {
        if(reference.player.used)
        {
            SPEED -= 80;
            reference.player.used = false;
            aux = true;
        }
        if(aux) {
            if (slowTimer.TimeElapsed()) {
                reference.player.used = false;
                SPEED = 200;
                aux = false;
            }
        }
    }
    private void WasAttacked()
    {
        /*
        if(IS_IN_RANGE(reference.player, 30))
        {
            if(Gdx.input.isKeyPressed(Input.Keys.A) && reference.player.ultimateUsed)
            {
                reference.player.DAMAGE -= 100;
                reference.player.ultimateUsed = false;
            }
        }
        */
        if(IS_IN_RANGE(reference.player, 30))
        {
            if( Gdx.input.isKeyPressed(Input.Keys.Q) )
            {
                reference.player.DAMAGE -= 100;
            }
        }
    }

    private boolean expAdded = false;
    @Override
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
        nameLabel.Draw("Chapo");
    }
}
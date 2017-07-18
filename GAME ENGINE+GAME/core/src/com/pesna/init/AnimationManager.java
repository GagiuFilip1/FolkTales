package com.pesna.init;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.pesna.Main;

public class AnimationManager {

	class AnimationLoader
	{
		String filename;
		public String atlas;
		public int frames;
		public float speed;
		public AnimationLoader( String _filename, String _atlas, int _frames, float _speed)
		{
			filename = _filename;           frames = _frames;
			atlas = _atlas;                 speed = _speed;
		}
		static final String subpath = "animations/";
	}

	public ArrayList<AnimationLoader> loaders = new ArrayList<AnimationLoader>();
	public HashMap <String,Animation> animations = new HashMap<String,Animation>();

	public Animation stay,attack,fall,walk; // stickman stuff
	public Animation hwalk,hstay,hattack; // horse stuff ( player )
	public Animation eattack, eidle, erun; // enemy stuff

	public AnimationManager(Main mainClass)
	{
		this.init();
		this.registerTextures( mainClass.assetManager );
	}

	private void init()
	{
		//stickman stuff
		loaders.add( new AnimationLoader ( "attack", "player", 3, 0.2f) );
		loaders.add( new AnimationLoader ( "fall", "player", 4, 1f ) );
		loaders.add( new AnimationLoader ( "walk", "player", 5, 0.125f ) );
		loaders.add( new AnimationLoader ( "stay", "player", 1, 1f ) );

		//horse stuff
		loaders.add( new AnimationLoader ( "hwalk", "horse", 3, 0.125f ) );
		loaders.add( new AnimationLoader ( "hstay", "horse", 1, 1f ) );
		loaders.add( new AnimationLoader ( "hattack", "horse", 3, 0.125f ) );

		//enemy stuff
		loaders.add( new AnimationLoader ( "eattack", "enemy", 3, 0.300f ) );
		loaders.add( new AnimationLoader ( "eidle", "enemy", 1, 1f ) );
		loaders.add( new AnimationLoader ( "erun", "enemy", 3, 0.125f ) );
	}

	private void registerTextures( AssetManager assetManager )
	{
		assetManager.load( "animations/player.pack", TextureAtlas.class );
		assetManager.load( "animations/horse.pack", TextureAtlas.class );
		assetManager.load( "animations/enemy.pack", TextureAtlas.class );
	}

	public void assignTextures ( AssetManager assetManager )
	{
		for ( AnimationLoader loader : loaders )
		{
			int frames = loader.frames;
			TextureAtlas atlas = assetManager.get( AnimationLoader.subpath + loader.atlas + ".pack", TextureAtlas.class );

			AtlasRegion[] regions = new AtlasRegion[frames];

			for ( int i = 0; i < frames; i++ )
				regions[i] = atlas.findRegion( loader.filename + Integer.toString(i) ); //attack0, attack4

			animations.put( loader.filename, new Animation( loader.speed, regions ) );
			//System.out.println( "Loaded : " + loader.filename + " " + regions.length);
		}
		this.registerVariables();
	}

	private void registerVariables()
	{
		stay = animations.get( "stay" );
		attack = animations.get( "attack" );
		fall = animations.get( "fall" );
		walk = animations.get( "walk" );
		hwalk = animations.get( "hwalk" );
		hstay = animations.get( "hstay" );
		hattack=animations.get( "hattack" );
		eattack=animations.get( "eattack" );
		eidle = animations.get( "eidle" );
		erun = animations.get( "erun" );
	}
}
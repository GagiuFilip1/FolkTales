package com.pesna.init;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.brashmonkey.spriter.Data;
import com.brashmonkey.spriter.SCMLReader;
import com.pesna.Main;
import com.pesna.entities.BehaviourBundle;
import com.pesna.spriter.LibGdx.LibGdxDrawer;
import com.pesna.spriter.LibGdx.LibGdxLoader;
import com.pesna.spriter.LibGdx.SpriterAnimation;
import com.pesna.spriter.LibGdx.SpriterAnimationBundle;

import java.io.IOException;
import java.util.Scanner;

public class GameRegistry {
	private final Main reference;
	public ItemManager itemManager;
	public AnimationManager animationManager;
	public LevelManager levelManager;
	public final static int SpriterGraphicsMaxCount = 50;
	
	public GameRegistry( Main _reference )
	{
		reference = _reference;
		itemManager = new ItemManager( reference );
		animationManager = new AnimationManager( reference );
		levelManager = new LevelManager( reference );
		reference.assetManager.load("ItemsSprites/rock.png", Texture.class );
		reference.assetManager.load("ItemsSprites/wall.png", Texture.class );
		reference.assetManager.load("ItemsSprites/tree.PNG" , Texture.class);
		reference.assetManager.load("ItemsSprites/grass.png" , Texture.class);

		reference.assetManager.load("ItemsSprites/talentsBack.png" , Texture.class);

		reference.assetManager.load("quit.png" , Texture.class);
		reference.assetManager.load("play.png" , Texture.class);
		reference.assetManager.load("multiplayer.png" , Texture.class);
		reference.assetManager.load("quitL.png" , Texture.class);
		reference.assetManager.load("playL.png" , Texture.class);
		reference.assetManager.load("multiplayerL.png" , Texture.class);

		reference.assetManager.load( "menu.png", Texture.class );
		reference.assetManager.load( "buttons.png", Texture.class );

		reference.assetManager.load ( "items/arrow.png", Texture.class );

		loadSpriterInfo();
	}
	public void onAssetsLoaded( Main reference ) throws IOException {
		//Replace those assignTextures with the constructor.. for some cases TODO
		itemManager.assignTextures( reference.assetManager ); // assign textures to each item
		animationManager.assignTextures( reference.assetManager );
		levelManager.assignTextures( reference.assetManager );
		
		//At last
		reference.screenManager.onAssetsLoaded( reference );
	}


	public com.brashmonkey.spriter.Data werewolfData;
	public LibGdxLoader werewolfLoader;

	public com.brashmonkey.spriter.Data pesnaIdleData;
	public LibGdxLoader pesnaIdleLoader;

	public com.brashmonkey.spriter.Data pesnaMoveData;
	public LibGdxLoader pesnaMoveLoader;

	public Data[] spriterData = new Data[SpriterGraphicsMaxCount];
	public LibGdxLoader[] spriterLoader = new LibGdxLoader[SpriterGraphicsMaxCount];
	public LibGdxDrawer[] spriterDrawer = new LibGdxDrawer[SpriterGraphicsMaxCount];

	public SpriterAnimationBundle[] animationBundles = new SpriterAnimationBundle[SpriterGraphicsMaxCount];
	public BehaviourBundle[] behaviourBundles = new BehaviourBundle[SpriterGraphicsMaxCount];

	private void loadSpriterInfo()
	{
		//READING FROM FILES PART \/

		FileHandle modRoot;
		FileHandle handle;

		String directory = System.getProperty("user.dir");
		modRoot = Gdx.files.absolute(directory);
		modRoot = modRoot.parent().parent();
		System.out.println(modRoot.path());

		handle = modRoot.child("story.txt");

		if ( handle.exists() )
			System.out.println("File Found!");
		else
			System.out.println("File NOT Found!");

		Scanner scanner = new Scanner(handle.read());

		int length = 0;

		String[] assetList = new String[SpriterGraphicsMaxCount];

		length = scanner.nextInt();
		scanner.nextLine();
		for ( int i = 0; i < length; i++ )
		{
			assetList[i] = scanner.nextLine();
			System.out.println(assetList[i]);
		}

		//System.out.println(directory);

		//TODO : Here you create the list of filenames for scmls to be loaded ( order matters ), and also read the animation configurations
		/*
		String[] assetList = { "werewolf/Mircea.json.scml",
				"martinica/martinica.scml",
				"lucifer/NewProject.autosave.scml"
		};*/

		int animationSpeed=1, entityID=0, animationID=0, yOffset=0;
		boolean mainLoader=true;
		float projectionScale=0.5f;

		for ( int index = 0; index < length; index++ )
		{
			animationBundles[index] = new SpriterAnimationBundle();
			//read (int animationSpeed, int entityID, int animationID, int yOffset, boolean mainLoader, float projectionScale )
			//read for run
			animationSpeed = scanner.nextInt();
			entityID = scanner.nextInt();
			animationID = scanner.nextInt();
			yOffset = scanner.nextInt();
			//mainLoader = scanner.nextBoolean();
			projectionScale = scanner.nextFloat();
			animationBundles[index].run  = new SpriterAnimation( animationSpeed, entityID, animationID, yOffset, mainLoader, projectionScale );
			//read for idle
			animationSpeed = scanner.nextInt();
			entityID = scanner.nextInt();
			animationID = scanner.nextInt();
			yOffset = scanner.nextInt();
			//mainLoader = scanner.nextBoolean();
			projectionScale = scanner.nextFloat();
			animationBundles[index].idle = new SpriterAnimation( animationSpeed, entityID, animationID, yOffset, mainLoader, projectionScale );
			//read for attack
			animationSpeed = scanner.nextInt();
			entityID = scanner.nextInt();
			animationID = scanner.nextInt();
			yOffset = scanner.nextInt();
			//mainLoader = scanner.nextBoolean();
			projectionScale = scanner.nextFloat();
			animationBundles[index].attack=new SpriterAnimation( animationSpeed, entityID, animationID, yOffset, mainLoader, projectionScale );
		}

		for ( int index = 0; index < length; index++ )
		{
			behaviourBundles[index] = new BehaviourBundle();
			behaviourBundles[index].ATTACK_SPEED = scanner.nextFloat();
			behaviourBundles[index].SPEED = scanner.nextFloat();
			behaviourBundles[index].HP = scanner.nextInt();
			behaviourBundles[index].CHASE_RANGE = scanner.nextInt();
			behaviourBundles[index].ATTACK_RANGE = scanner.nextInt();
		}

		//READING FROM FILES PART /\


		//Processing read information \/
		for ( int index = 0; index < length; index++ )
		{
			handle = Gdx.files.internal( assetList[index] );
			spriterData[index] = new SCMLReader( handle.read() ).getData();
			spriterLoader[index] = new LibGdxLoader( spriterData[index] );
			spriterLoader[index].load( handle.file() );
			spriterDrawer[index] = new LibGdxDrawer( spriterLoader[index], reference.batch, reference.shapeRenderer );
		}


		//"harapalb/2dshit.scml"
		//"werewolf/Mircea.json.scml"
		//"martinica/martinica.scml"
		//"lucifer/NewProject.autosave.scml"
		/*
		FileHandle handle = Gdx.files.internal("martinica/martinica.scml");
		werewolfData = new SCMLReader( handle.read() ).getData();
		werewolfLoader = new LibGdxLoader(werewolfData);
		werewolfLoader.load(handle.file());
		reference.werewolfDrawer = new LibGdxDrawer(werewolfLoader, reference.batch, reference.shapeRenderer);
		*/
		//handle = Gdx.files.internal( "harapalbb/harapalbb.scml" );
		handle = Gdx.files.internal( "resized-shot/resized-shot.scml" );
		pesnaIdleData = new SCMLReader( handle.read() ).getData();
		pesnaIdleLoader = new LibGdxLoader( pesnaIdleData );
		pesnaIdleLoader.load(handle.file());
		reference.pesnaIdleDrawer = new LibGdxDrawer( pesnaIdleLoader, reference.batch, reference.shapeRenderer );

		//handle = Gdx.files.internal( "harapalbb/harapalbb.scml" );
		handle = Gdx.files.internal( "harapalbb/harapalbb.scml" );
		pesnaMoveData = new SCMLReader( handle.read() ).getData();
		pesnaMoveLoader = new LibGdxLoader( pesnaMoveData );
		pesnaMoveLoader.load(handle.file());
		reference.pesnaMoveDrawer = new LibGdxDrawer( pesnaMoveLoader, reference.batch, reference.shapeRenderer );
	}
}

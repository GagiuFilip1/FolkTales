package com.mygdx.game.editorCore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Main;
import com.mygdx.game.abstracts.screenObject;
import com.mygdx.game.chatModules.XMPPbuilder;
import com.mygdx.game.objects.Square;

/**
 * Created by Teo on 4/27/2017.
 */
public class ColaborationModule
{

    public final XMPPbuilder onlineBuilder;
    private final Main ref;
    public ColaborationModule(Main _ref)
    {
        onlineBuilder = new XMPPbuilder();
        ref = _ref;
    }

    public void Receive()
    {
        if (onlineBuilder.socketBuilder.platform != null)
        {
            screenObject sq = new Square(ref);
            sq = ref.editorFunctions.AddPLatform(((Square) sq));
            ((Square) sq).SetTexture(new Texture(Gdx.files.internal(onlineBuilder.socketBuilder.platform )));
            ref.editor.addToRuntime(sq, 1);
            ref.mapCreator.putData("platform " + onlineBuilder.socketBuilder.platform );
            onlineBuilder.socketBuilder.platform = null;
        }
        if (onlineBuilder.socketBuilder.background != null)
        {
            screenObject sq = new Square(ref);
            sq = ref.editorFunctions.AddBackground(((Square) sq));
            ((Square) sq).SetTexture(new Texture(Gdx.files.internal(onlineBuilder.socketBuilder.background)));
            ref.editor.addToRuntime(sq, 1);
            ref.mapCreator.putData("background " + onlineBuilder.socketBuilder.background);
            onlineBuilder.socketBuilder.background = null;
        }
        if(onlineBuilder.socketBuilder.story != null)
        {
            ref.mapCreator.putData("story " + onlineBuilder.socketBuilder.story);
            onlineBuilder.socketBuilder.story = null;
        }
        if(onlineBuilder.socketBuilder.level != null)
        {
            ref.mapCreator.putData("level " + onlineBuilder.socketBuilder.level);
        }
    }
}

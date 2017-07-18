package com.mygdx.game.editorCore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Main;
import com.mygdx.game.abstracts.screenObject;
import com.mygdx.game.objects.Square;

/**
 * Created by Teo on 4/27/2017.
 */
public class ColaborationModule
{

    public final ColaborationOn onlineBuilder;
    private final Main ref;
    public ColaborationModule(Main _ref)
    {
        onlineBuilder = new ColaborationOn();
        ref = _ref;
    }

    public void Receive()
    {
        if (onlineBuilder.platform != null)
        {
            screenObject sq = new Square(ref);
            sq = ref.editorFunctions.AddPLatform(((Square) sq));
            ((Square) sq).SetTexture(new Texture(Gdx.files.internal(onlineBuilder.platform)));
            ref.editor.addToRuntime(sq, 1);
            ref.mapCreator.putData("platform " + onlineBuilder.platform);
            onlineBuilder.platform = null;
        }
        if (onlineBuilder.background != null)
        {
            screenObject sq = new Square(ref);
            sq = ref.editorFunctions.AddBackground(((Square) sq));
            ((Square) sq).SetTexture(new Texture(Gdx.files.internal(onlineBuilder.background)));
            ref.editor.addToRuntime(sq, 1);
            ref.mapCreator.putData("background " + onlineBuilder.background);
            onlineBuilder.background = null;
        }
        if(onlineBuilder.story != null)
        {
            ref.mapCreator.putData("story " + onlineBuilder.story);
            onlineBuilder.story = null;
        }
        if(onlineBuilder.level != null)
        {
            ref.mapCreator.putData("level " + onlineBuilder.level);
        }
    }
}

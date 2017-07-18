package com.pesna.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by Gagiu on 1/19/2017.
 */
public class SoundManager
{
  private Sound sound;
  private long id ;
  private boolean IS_PLAYING;
    public SoundManager(String _subPath)
    {
      sound = Gdx.audio.newSound(Gdx.files.internal(_subPath));
      id = sound.loop(1.0f);
      sound.stop();
    }

    public void PLay()
    {
      sound.play();
      IS_PLAYING = true;
    }

    public void Pause()
    {
      sound.pause();
      IS_PLAYING = false;
    }

    public void Dispose()
    {
      sound.dispose();
      IS_PLAYING = false;
    }

    public void SetVolume(float volume)
    {
      sound.setVolume(id,volume);
    }

    public void LoopSound(boolean loopCheck)
    {
      if(loopCheck)
      {
        sound.setLooping(id,true);
      }
    }

    public boolean IsPlaying()
    {
      return IS_PLAYING;
    }

}

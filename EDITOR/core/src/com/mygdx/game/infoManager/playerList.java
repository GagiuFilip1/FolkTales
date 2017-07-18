package com.mygdx.game.infoManager;

import com.mygdx.game.Main;
import com.mygdx.game.abstracts.screenObject;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Gagiu on 3/26/2017.
 */
public class playerList extends screenObject
{

  private Main ref;
  private HashMap<String , String> playerInfo;
  public playerList(Main _reference , LinkedList<String> players , LinkedList<String> Ids)
  {
      ref = _reference;
      playerInfo = new HashMap<>();
      players = new LinkedList<>();
      Ids = new LinkedList<>();
      players.add("player1");
      players.add("player2");
      Ids.add("12313");
      Ids.add("asdadsa");
      AddInfo(players , Ids);
      System.out.println(playerInfo.entrySet());
  }

  @Override
  public void Render() {

  }

  @Override
  public void Update() {

  }

  @Override
  public void Destroy() {

  }

  private void AddInfo(LinkedList<String> a , LinkedList<String> b)
  {
    int index = 0;
    for(String player : a) {
      playerInfo.put(player , b.get(index));
      index++;
    }
  }
}

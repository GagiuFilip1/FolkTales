package com.mygdx.game.editorCore;

import io.socket.client.IO;
import io.socket.client.Socket;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedList;

public class ColaborationOn
{

  public io.socket.client.Socket socket;
  public String background,texture,platform,spawner,boss,level,story, room ;
  public String playerName;
  public String playerId;
  public String myID = "";
  public String myUsername = "";
  private LinkedList<String> playerNames = new LinkedList<>();
  private LinkedList<String> playerIds = new LinkedList<>();
  private HashMap<String , Integer> party = new HashMap<>();
  public String senderId, senderName;
  public boolean drawRequest = false;
  public void start(){
    Connect();
  }
  private void  Connect(){

    try {
      socket = IO.socket("http://localhost:3000/");
      socket.on(Socket.EVENT_CONNECT, args -> {
        JSONObject obj = new JSONObject();
        obj.put("username", myUsername);
        socket.emit("SetUsername",obj);
        socket.emit("get-online-players");
      }).on("getting-background", args -> {
        JSONObject obj = (JSONObject) args[0];
        background = obj.getString("background");
       // drawRequest = true;

      }).on("getting-texture", args -> {
        JSONObject obj = (JSONObject) args[0];
        texture = obj.getString("texture");
       // drawRequest = true;

      }).on("getting-platform", args -> {
        JSONObject obj = (JSONObject) args[0];
        platform = obj.getString("platform");
        System.out.println("am primit " + platform);
       // drawRequest = true;

      }).on("getting-spawner", args -> {
        JSONObject obj = (JSONObject) args[0];
        spawner = obj.getString("spawner");
       // drawRequest = true;

      }).on("getting-boss", args -> {
        JSONObject obj = (JSONObject) args[0];
        boss = obj.getString("boss");
       // drawRequest = true;

      }).on("getting-level", args -> {
        JSONObject obj = (JSONObject) args[0];
        level = obj.getString("level");
       // drawRequest = true;

      }).on("getting-story", args -> {
        JSONObject obj = (JSONObject) args[0];
        story = obj.getString("story");
      }).on("receiving-online-players", args -> {
        JSONArray Ids = (JSONArray) args[0];
        JSONArray Names = (JSONArray) args[1];
        try {
          playerIds.clear();
          playerNames.clear();
          for(int i = 0; i < Ids.length(); i++){
            playerIds.add(Ids.get(i).toString());
            playerNames.add(Names.get(i).toString());
          }
        } catch(JSONException e){}
        int index = 0;
        for(String s: playerIds){
          try {
            //    System.out.println(s + " " + playerNames.get(index++));
          }
          catch (Exception ex){}
        }
        //    System.out.println("\n");

      }).on("receiving-invite", args -> {
        JSONObject obj = (JSONObject) args[0];
        senderId = obj.getString("senderId");
        senderName = obj.getString("senderName");
        drawRequest = true;
      }).on("battle-accepted", args -> {
        //TODO:load the arena
        JSONObject obj = (JSONObject) args[0];
        room = obj.getString("room");
        party.put(obj.getString("receiverName") , Integer.valueOf(obj.getString("receiverId")));
        // System.out.println("The battle between "+ socket.id() +
        //" and " + enemyId + " is about to begin.");
      }).on("batted-declined", args -> {
        JSONObject obj = new JSONObject();
        obj.put("room", room);
        socket.emit("leave-room",obj);

      }).on("new-private-message", args -> {
        String usern,mess;
        JSONObject jobj = (JSONObject)args[0];
        usern = jobj.getString("username");
        mess = jobj.getString("message");
        // System.out.println(usern + ": " + mess);

      }).on("playerDisconnected", args -> socket.emit("get-online-players"));

      socket.connect();
      myID = socket.id();
      new java.util.Timer().schedule(
              new java.util.TimerTask() {
                @Override
                public void run() {
                  // sendInvite(playerIds.get(0));
                }},3000);
      //
      new java.util.Timer().schedule(new java.util.TimerTask() {
        @Override public void run() {
          // newPosition(room,10,11);
          //  attack(room,enemyId,30);
        }},5000);
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
  }
  public void sendBackground(String background){
    JSONObject obj = new JSONObject();
    obj.put("background", background);
    socket.emit("sendBackground",obj);
  }

  public void sendTexture(String texture){
    JSONObject obj = new JSONObject();
    obj.put("texture", texture);
    socket.emit("sendTexture",obj);
  }

  public void sendPlatform(String platform){
    JSONObject obj = new JSONObject();
    obj.put("platform", platform);
    socket.emit("sendPlatform",obj);
  }

  public void sendSpawner(String spawner){
    JSONObject obj = new JSONObject();
    obj.put("spawner", spawner);
    socket.emit("sendSpawner",obj);


  } public void sendBoss(String boss){
  JSONObject obj = new JSONObject();
  obj.put("boss", boss);
  socket.emit("sendBoss",obj);
}
  public void sendLevel(String level){
    JSONObject obj = new JSONObject();
    obj.put("level", level);
    socket.emit("sendLevel",obj);
  }
  public void sendStory(String story){
    JSONObject obj = new JSONObject();
    obj.put("story", story);
    socket.emit("sendStory",obj);
  }

    public String GetID(){
        return socket.id();
    }

    public void sendInvite(String receiverId){
        JSONObject obj = new JSONObject();
        obj.put("receiverId", receiverId);
        obj.put("initiatorName", myUsername);
        socket.emit("send-invite",obj);
        //  System.out.println("you invited "+receiverId);
    }

    public void respondInvite(String senderId,String senderName , String responseInvite){
        JSONObject obj = new JSONObject();
        obj.put("isAccepted", responseInvite);
        obj.put("senderId", senderId);
        obj.put("senderName",senderName);
        obj.put("receiverId",socket.id());
        obj.put("receiverName",myUsername);
        socket.emit("responded-invite",obj);
        if(responseInvite.equals("1")){
            room = senderId;
            //System.out.println("The battle between "+ socket.id() +
            // " and " + enemyId + " is about to begin.");
        }
        else
        {
            //  System.out.println("declined");
        }
    }

    public void newPosition(int x,int y){
        JSONObject obj = new JSONObject();
        obj.put("id", socket.id());
        obj.put("room",room);
        obj.put("x", x);
        obj.put("y", y);
        System.out.println("moving");
        socket.emit("NewPosition",obj);
    }

    public LinkedList<String> getPlayerNames()
    {
        return playerNames;
    }

    public LinkedList<String> getPlayerIds()
    {
        return  playerIds;
    }

    public void refresh()
    {

        try {
            playerIds.clear();
            playerNames.clear();
            socket.emit("get-online-players");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean Disconnect()
    {
        socket.disconnect();
        socket.close();
        socket.io().off();
        refresh();
        return playerIds.isEmpty();
    }
}

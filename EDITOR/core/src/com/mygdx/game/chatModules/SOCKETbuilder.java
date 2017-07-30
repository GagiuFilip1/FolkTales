package com.mygdx.game.chatModules;

import io.socket.client.IO;
import io.socket.client.Socket;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class SOCKETbuilder {
    public Socket socket;
    public String room;
    public boolean startEditor = false;
    public String background,texture,platform,spawner,boss,level,story ;

    public SOCKETbuilder(String username){
        Connect(username);
    }
    private void  Connect(String _myUsername ) {

        try {
            //socket = IO.socket("http://127.0.0.1:3000/");
            socket = IO.socket("http://localhost:3000/");
            // socket = IO.socket("http://pesnatest.azurewebsites.net/");
            socket.on(Socket.EVENT_CONNECT, args -> {
                try {
                    JSONObject obj = new JSONObject();
                    obj.put("username", _myUsername);
                    socket.emit("SetUsername", obj);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }).on("ownerStartEditor", args -> {
                //at least one friend accepted the request. Now start the editor
                joinSocketIoRoom(room);
                startEditor = true;
            }).on("getting-background", args -> {
                try {
                    JSONObject obj = (JSONObject) args[0];
                    background = obj.getString("background");
                    // drawRequest = true;
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }).on("getting-texture", args -> {
                try {
                    JSONObject obj = (JSONObject) args[0];
                    texture = obj.getString("texture");
                    // drawRequest = true;
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }).on("getting-platform", args -> {
                try {
                    JSONObject obj = (JSONObject) args[0];
                    platform = obj.getString("platform");
                    System.out.println("am primit " + platform);
                    // drawRequest = true;
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }).on("getting-spawner", args -> {
                try {
                    JSONObject obj = (JSONObject) args[0];
                    spawner = obj.getString("spawner");
                    // drawRequest = true;
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }).on("getting-boss", args -> {
                try {
                    JSONObject obj = (JSONObject) args[0];
                    boss = obj.getString("boss");
                    // drawRequest = true;
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }).on("getting-level", args -> {
                try {
                    JSONObject obj = (JSONObject) args[0];
                    level = obj.getString("level");
                    // drawRequest = true;
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }).on("getting-story", args -> {
                try {
                    JSONObject obj = (JSONObject) args[0];
                    story = obj.getString("story");
                    // drawRequest = true;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });

            socket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void joinSocketIoRoom(String room){
        try {
            JSONObject obj = new JSONObject();
            obj.put("room",room);
            socket.emit("joinSocketIoRoom",obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void tellOwnerStart(String chatOwnerSocketId){
        try {
            //we accepted the request and now we tell owner to start editor
            JSONObject obj = new JSONObject();
            obj.put("chatOwnerSocketId",chatOwnerSocketId);
            socket.emit("tellOwnerStart",obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void sendBackground(String background){
        try {
            JSONObject obj = new JSONObject();
            obj.put("background", background);
            obj.put("room",room);
            socket.emit("sendBackground",obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void sendTexture(String texture){
        try {
            JSONObject obj = new JSONObject();
            obj.put("texture", texture);
            obj.put("room",room);
            socket.emit("sendTexture",obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void sendPlatform(String platform){
        try{
            JSONObject obj = new JSONObject();
            obj.put("platform", platform);
            obj.put("room",room);
            socket.emit("sendPlatform",obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void sendSpawner(String spawner){
        try{
            JSONObject obj = new JSONObject();
            obj.put("spawner", spawner);
            obj.put("room",room);
            socket.emit("sendSpawner",obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void sendBoss(String boss){
        try{
            JSONObject obj = new JSONObject();
            obj.put("boss", boss);
            obj.put("room",room);
            socket.emit("sendBoss",obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void sendLevel(String level){
        try{
            JSONObject obj = new JSONObject();
            obj.put("level", level);
            obj.put("room",room);
            socket.emit("sendLevel",obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void sendStory(String story){
        try{
            JSONObject obj = new JSONObject();
            obj.put("story", story);
            obj.put("room",room);
            socket.emit("sendStory",obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}

var express = require('express');
var app = express();
var server = require('http').Server(app);//a node server that uses express as the boiler plate
var io = require('socket.io')(server);//a socket, pass the server as a parameter to it
var port = process.env.PORT || 3000;//the port the server listens to
var sockets=[];
var socketIds=[];
var userNames=[];

//Listen
server.listen(port, function () {
    console.log('Server listening at port %d', port);
});

io.on('connect', function(socket){
    socket.on("SetUsername",function(data){
        sockets.push(socket);
        socketIds.push(socket.id);
        userNames.push(data.username);
        console.log(data.username +"("+ socket.id + ")" + " connected.");
    });

    socket.on("tellOwnerStart",function (data) {
        socket.broadcast.to(data.chatOwnerSocketId).emit("ownerStartEditor");
    });
    socket.on("joinSocketIoRoom",function (data) {
        socket.join(data.room);
    })

    socket.on("leave-room",function (data) {
        socket.leave(data.room);
    });

    socket.on("sendBackground",function (data) {
        socket.broadcast.to(data.room).emit("getting-background",{background:data.background});
    });

    socket.on("sendTexture",function (data) {
        socket.broadcast.to(data.room).emit("getting-texture",{texture:data.texture});
    });

    socket.on("sendPlatform",function (data) {
        socket.broadcast.to(data.room).emit("getting-platform",{platform:data.platform});
    });

    socket.on("sendSpawner",function (data) {
        socket.broadcast.to(data.room).emit("getting-spawner",{spawner:data.spawner});
    });

    socket.on("sendBoss",function (data) {
        socket.broadcast.to(data.room).emit("getting-boss",{boss:data.boss});
    });

    socket.on("sendLevel",function (data) {
        socket.broadcast.to(data.room).emit("getting-level",{level:data.level});
    });

    socket.on("sendStory",function (data) {
        socket.broadcast.to(data.room).emit("getting-story",{story:data.story});
    });

    socket.on('disconnect', function(){
        console.log("Player Disconnected");
        socket.broadcast.emit('playerDisconnected', { id: socket.id });
        for(var i = 0; i < sockets.length; i++){
            if(sockets[i].id == socket.id){
                sockets.splice(i, 1);
                socketIds.splice(i,1);
                userNames.splice(i,1);
            }
        }
    });
});
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
var express = require('express');
var app = express();
var server = require('http').Server(app);//a node server that uses express as the boiler plate
var io = require('socket.io')(server);//a socket, pass the server as a parameter to it
var port = process.env.PORT || 3000;//the port the server listens to
var sockets=[];
var socketIds=[];
var userNames=[];

//Listen
server.listen(port, function () {
    console.log('Server listening at port %d', port);
});

io.on('connect', function(socket){
    socket.on("SetUsername",function(data){
        sockets.push(socket);
        socketIds.push(socket.id);
        userNames.push(data.username);
        //socket.emit('PlayerConnected',{id:socket.id, username:"You"});
        //socket.broadcast.emit('PlayerConnected',{id:socket.id, username:data.username});
        console.log(data.username +"("+ socket.id + ")" + " connected.");
    });

    socket.on("get-online-players",function () {
        socket.emit("receiving-online-players",socketIds,userNames);
    });

    socket.on("send-invite",function (data) {
        socket.join(socket.id + data.receiverId);
        socket.broadcast.to(data.receiverId)
            .emit("receiving-invite",{
                senderId:socket.id,
                senderName:data.initiatorName});
        console.log(socket.id + " sent "+ data.receiverId +" an invite.");
    });

    socket.on("responded-invite",function (data) {
        if(data.isAccepted=="1"){
            socket.join(data.senderId + socket.id);
            socket.broadcast.to(data.senderId + socket.id).
            emit("battle-accepted",{
                room:data.senderId + socket.id,
                receiverId:data.receiverId,
                receiverName:data.receiverName});
        }
        else{
            socket.broadcast.to(data.senderId).emit("batted-declined");
        }
    });

socket.on("leave-room",function (data) {
    socket.leave(data.room);
});

socket.on("NewPosition",function (data) {
    console.log(data.id +"(room:"+ data.room + " " + data.x + " " + data.y);
    socket.broadcast.to(data.room)
        .emit("ReceivingEnemyPos",{
            id:data.id,
            x:data.x,
            y:data.y});
});

socket.on("attack",function (data) {
    socket.broadcast.to(data.room).to(data.receiverId).
    emit("attacked",{
        dmgReceived:data.dmgAmount
    })
});
socket.on("request-health",function (data) {
    socket.broadcast.to(data.room).to(data.enemyId).emit("request-to-get-health");
});
socket.on("sendHealth",function (data) {
    socket.broadcast.to(data.room).to(data.enemyId).emit("getting-health",{enemyHealth:data.Health});
});
socket.on('disconnect', function(){
    console.log("Player Disconnected");
    socket.broadcast.emit('playerDisconnected', { id: socket.id });
    for(var i = 0; i < sockets.length; i++){
        if(sockets[i].id == socket.id){
            sockets.splice(i, 1);
            socketIds.splice(i,1);
            userNames.splice(i,1);
        }
    }
});
});

 */
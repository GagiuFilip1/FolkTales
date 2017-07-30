package com.mygdx.game.chatModules;

import org.jivesoftware.smack.*;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.roster.AbstractRosterListener;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.iqregister.AccountManager;
import org.jivesoftware.smackx.muc.InvitationListener;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.muc.MultiUserChatException;
import org.jivesoftware.smackx.muc.MultiUserChatManager;
import org.jivesoftware.smackx.muc.packet.MUCUser;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.EntityJid;
import org.jxmpp.jid.Jid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.jid.parts.Localpart;
import org.jxmpp.jid.parts.Resourcepart;
import org.jxmpp.stringprep.XmppStringprepException;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

public class XMPPbuilder {
    public  HashMap<String,String> _friendList;
    public  String _myUsername = "e4";
    //private  String _friendUsername = "e4";
    private  AbstractXMPPConnection conn;//connection for the XMPP server
    private  Roster roster;
    private  MultiUserChat multiUserChat;
    private  MultiUserChatManager MUCmanager;
    private  Resourcepart nickname;

    public SOCKETbuilder socketBuilder;

    public void SetCredentials(String myUsername , String myPassword , boolean login)
    {
        if(login){
            _myUsername = myUsername;
            login(myUsername, myPassword);
            socketBuilder  = new SOCKETbuilder(myUsername);
        }
        else{
            register_user(myUsername, myPassword);
            _myUsername = myUsername;
            login(myUsername, myPassword);
            socketBuilder  = new SOCKETbuilder(myUsername);
        }
    }

    private void login(String username, String password){
        //connecting and logging in
        try {
            conn = connect_XMPP();
            if(conn.isConnected()){
                //logging in
                conn.login(username,password);
            }
        } catch (SmackException|IOException|XMPPException|InterruptedException e) {
            e.printStackTrace();
        }
        admitFriendsRequest();
        init_Roster();
    }

    private void register_user(String username, String password){
        try {
            conn = connect_XMPP();
            conn.login("acccr", "12345");

            // Now we create the account:
            Localpart username_localpart = Localpart.from(username);
            AccountManager accountManager = AccountManager.getInstance(conn);

            if (accountManager.supportsAccountCreation()) {
                accountManager.sensitiveOperationOverInsecureConnection(true);
                accountManager.createAccount(username_localpart, password);
            }
            conn.disconnect();
        }
        catch (InterruptedException | IOException | SmackException | XMPPException e) {
            e.printStackTrace();
        }
    }

    public void add_toRoster(String username){
        try {
            //choosing the group to add user
            String groups[]=new String[1];
            groups[0]="Friends";
            //create jid for user
            Jid jid = JidCreate.from(username+"@localhost");
            System.out.println("Friend's jid is"+jid);
            //create entry to Friends list
            roster.createEntry(jid.asBareJid(),"",groups);
            System.out.println("Friend's bare jid is"+jid.asBareJid());
        } catch (SmackException.NotConnectedException | SmackException.NotLoggedInException | XmppStringprepException | InterruptedException | SmackException.NoResponseException
                | XMPPException.XMPPErrorException e) {
            e.printStackTrace();
        }
    }

    public void changePresence(String presence_name){
        try {
            Presence presence = new Presence(Presence.Type.available);
            //String presence_name = presenceComboBox.getSelectedItem().toString();
            switch(presence_name){
                case "Online": presence.setMode(Presence.Mode.available);break;
                case "Free to chat": presence.setMode(Presence.Mode.chat);break;
                case "Away": presence.setMode(Presence.Mode.away);break;
                case "Extended away": presence.setMode(Presence.Mode.xa);break;
                case "Do not disturb": presence.setMode(Presence.Mode.dnd);break;
            }
            // Send the stanza (assume we have an XMPPConnection instance called "con").
            conn.sendStanza(presence);
        } catch (SmackException.NotConnectedException | InterruptedException e1) {
            e1.printStackTrace();
        }
    }



    //MUC

    public void create_multiChat() {
        try {
            // Create the XMPP address (JID) of the MUC.
            Jid mucJid = JidCreate.bareFrom(_myUsername + "@conference.localhost");
            // Create the nickname.
            nickname = Resourcepart.from(_myUsername);
            // Get the MultiUserChatManager
            MUCmanager = MultiUserChatManager.getInstanceFor(conn);
            // Create a MultiUserChat using an CustomXMPPConnection for a room
            multiUserChat = MUCmanager.getMultiUserChat((EntityBareJid) mucJid);
            //add message listener
            multiUserChat.addMessageListener(new MessageListener() {
                @Override
                public void processMessage(Message message) {
                    System.out.println("Message listener Received message in send message: "
                            + (message != null ? message.getBody() : "NULL") + "  , Message sender :" + message.getFrom());
                }
            });
            //create the room and join it
            multiUserChat.create(nickname).makeInstant();
            System.out.println("You create a MUC room named "+ mucJid);
            socketBuilder.room = _myUsername;

        } catch (XmppStringprepException | MultiUserChatException.MissingMucCreationAcknowledgeException | SmackException.NotConnectedException | SmackException.NoResponseException | XMPPException.XMPPErrorException | InterruptedException | MultiUserChatException.NotAMucServiceException | MultiUserChatException.MucAlreadyJoinedException e) {
            e.printStackTrace();
        }
    }

    public void inviteToMUC(String username, String room ){
        try {
            //create jid for username
            username = username + "@localhost";
            Jid jid = JidCreate.from(username);
            //send the invite

            multiUserChat.invite(jid.asEntityBareJidIfPossible(), room);

            System.out.println("(inviteToMUC) friend's jid is " + jid.asEntityBareJidIfPossible()+"| and room is "+room);
        } catch (SmackException.NotConnectedException | InterruptedException | XmppStringprepException e) {
            e.printStackTrace();
        }
    }

    public void invitationListener(){
        // listen for multi user chat invitations
        MultiUserChatManager.getInstanceFor(conn).addInvitationListener(new InvitationListener() {
            @Override
            public void invitationReceived(XMPPConnection connection, MultiUserChat room, EntityJid inviter, String reason, String password, Message message, MUCUser.Invite invitation) {
                MUCmanager = MultiUserChatManager.getInstanceFor(connection);
                try {
                    System.out.println("invitation received, room="+reason);
                    String name = inviter.toString().replace("@localhost","");
                    multiUserChat = MUCmanager.getMultiUserChat((EntityBareJid) JidCreate.bareFrom(name+"@conference.localhost"));
                    //reason owner socket_id

                    socketBuilder.room = reason;

                    socketBuilder.tellOwnerStart(reason);
                    socketBuilder.joinSocketIoRoom(reason);
                } catch (XmppStringprepException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println(Resourcepart.from(_myUsername));
                    System.out.println(multiUserChat);
                    multiUserChat.join(Resourcepart.from(_myUsername));
                    multiUserChat.addMessageListener(new MessageListener() {
                        @Override
                        public void processMessage(Message message) {
                            System.out.println("Message listener Received message in send message: "
                                    + (message != null ? message.getBody() : "NULL") + "  , Message sender :" + message.getFrom());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void sendMessageToMUC(String message){
        try {
            multiUserChat.sendMessage(message);
        } catch (SmackException.NotConnectedException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    //END MUC




    private AbstractXMPPConnection connect_XMPP(){
        try {
            //config the connection
            XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                    .setHost("localhost")
                    .setXmppDomain("localhost")
                    .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
                    .setSendPresence(true)
                    .setDebuggerEnabled(true)
                    .build();
            AbstractXMPPConnection conn = new XMPPTCPConnection(config);
            try {
                //create the connection
                conn.connect();
                return conn;
            } catch (SmackException | InterruptedException | XMPPException | IOException e) {
                e.printStackTrace();
                return null;
            }
        } catch (XmppStringprepException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void admitFriendsRequest() {
        //adding friends request listener
        conn.addAsyncStanzaListener(new StanzaListener(){
            public void processStanza(Stanza paramPacket) {
                System.out.println("\n");
                if (paramPacket instanceof Presence) {

                    Presence presence = (Presence) paramPacket;
                    Jid jid = presence.getFrom();
                    System.out.println("chat invite status changed by user: : "
                            + jid + " calling listner");
                    System.out.println("presence: " + presence.getFrom()
                            + "; type: " + presence.getType() + "; to: "
                            + presence.getTo() + "; " + presence.toXML());

                    try {
                        if (presence.getType().equals(Presence.Type.subscribe)) {
                            String groups[]=new String[1];
                            groups[0]="Friends";
                            roster.createEntry(presence.getFrom().asBareJid(),"",groups);

                            Presence newp = new Presence(Presence.Type.subscribed);
                            newp.setMode(Presence.Mode.available);
                            newp.setPriority(24);
                            newp.setTo(presence.getFrom().asBareJid());

                            Presence subscription = new Presence(
                                    Presence.Type.subscribe);
                            subscription.setTo(presence.getFrom().asBareJid());
                            conn.sendStanza(subscription);
                            conn.sendStanza(newp);
                        }
                        else if (presence.getType().equals(
                                Presence.Type.unsubscribe)) {
                            Presence newp = new Presence(Presence.Type.unsubscribed);
                            newp.setMode(Presence.Mode.available);
                            newp.setPriority(24);
                            newp.setTo(presence.getFrom());
                            conn.sendStanza(newp);
                        }
                    }
                    catch (SmackException.NotConnectedException | XMPPException.XMPPErrorException | SmackException.NoResponseException | SmackException.NotLoggedInException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new StanzaFilter() {
            public boolean accept(Stanza packet) {
                if (packet instanceof Presence) {
                    Presence presence = (Presence) packet;
                    if (presence.getType().equals(Presence.Type.subscribed)
                            || presence.getType().equals(
                            Presence.Type.subscribe)
                            || presence.getType().equals(
                            Presence.Type.unsubscribed)
                            || presence.getType().equals(
                            Presence.Type.unsubscribe)) {
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private void init_Roster(){
        //create roster
        roster = Roster.getInstanceFor(conn);
        roster.setSubscriptionMode(Roster.SubscriptionMode.accept_all);
        //initialise the list of friends
        _friendList = new HashMap<>();
        //adding roster listener
        roster.addRosterListener(new AbstractRosterListener(){
            // Ignored events
            public void entriesAdded(Collection address) {
                System.out.println("entry added: " + address);
                for (int i = 0; i < address.toArray().length; i++) {
                    _friendList.put(address.toArray()[i].toString(),"");
                    System.out.println( address.toArray()[i].toString());
                }

            }
            public void entriesDeleted(Collection address) {
                System.out.println("entry deleted: " + address);
            }
            public void entriesUpdated(Collection address) {
                System.out.println("entry updated: " + address);
            }
            public void presenceChanged(Presence presence) {
                //_friendList.replace()
                _friendList.put(presence.getFrom().asBareJid().toString(),presence.getMode().toString());
                System.out.println("Presence changed: " + presence.getFrom().asBareJid() +"|Mode: "+presence.getMode() + "| Status: " + presence.getStatus());
            }
        });
    }

}

package com.server;

import com.utility.Message;
import com.utility.UserInfo;
import com.utility.Utilities;

public class Slave extends Thread {
	
	private Connection master;
	private String username, password;
	private UserInfo userinformation;
	private String[] params;
	
	public Slave(Connection master) {
		this.master = master;
	}
	
	private void setParams(int length, String... p) {
		params = new String[length];
		for(int i = 0; i < length; i++) {
			params[i] = p[i];
		}
	}
	
	public void decode(Message message) {
		switch (message.getCommand()) {
		case "L100": // Login 
			username = message.getParams()[0];
			password = message.getParams()[1];
			if(Server.db.getRegisteredUsers().containsKey(username)) {
				userinformation = (UserInfo) message.getObject();
				if(Server.db.getRegisteredUsers().get(username).getPassword().equals(password)) {
					master.setLoggedIn(true);
					master.sendMessage("L100", null,userinformation);
				} else {
					setParams(1, "Wrong Password");
					master.sendMessage("L400", params);
				}
			} else {
				setParams(1, "Username doesn't exist.");
				master.sendMessage("L400", params);
			}
			break;
		case "L101": // Create User
			// Notify client if username already exists in database.
			userinformation = (UserInfo) message.getObject(); // Test if conversion is good.
			username = userinformation.getUsername();
			if(!Server.db.getRegisteredUsers().containsKey(username)) {
				Server.db.registerNewUser(username, userinformation);
				Server.setServerStatus("User " + username + " added to database.");
				master.sendMessage("L101", null);
			} else { // Username taken
				setParams(1, "Username already exists.");
				master.sendMessage("L401", params);
			}
			break;
		case "L102": // Change Password
			if(master.isLoggedIn()) {
				username = message.getParams()[0];
				password = message.getParams()[1];	
				if(Server.db.getRegisteredUsers().get(username).getPassword().equals(password)) {
					Server.db.getRegisteredUsers().get(username).setPassword(message.getParams()[2]);
				} else {
					setParams(1, "Wrong Password");
					master.sendMessage("L400", params);
				}
			} 
			break;
		case "L103": // Logout
			if(master.isLoggedIn()) {
				master.setLoggedIn(false);
			}
			break;
		case "C100": // Invite Private Chat
			break;
		case "C101": // Start Private Group
			break;
		case "C102": // Start Public Group
			String chatID = Utilities.generateID(5);
			if(Server.db.addNewPublicChat(message.getParams()[0], chatID)){
				master.sendMessage("C100", null);
			} else{
				setParams(1, "Roomname taken");
				master.sendMessage("C400", params);
			}	
			break;
		case "S100": // Broadcast Message
			System.out.println(message.toString());
			break;
		case "S101": // Private Message
			break;
		case "V100": // Add Friend
			break;
		case "V101": // Accept Friend
			break;
		case "V102": // Delete Friend
			break;
		case "V103": // Block Person
			break;
		case "G100": // Join Public Chat
			break;
		case "G101": // Join Private Group
			break;
		case "G102": // Remove Person from Chat
			break;
		case "F100": // Upload File
			break;
		case "F101": // Download File
			break;
		case "D100": // Get PublicRooms data
			master.sendMessage("D100", null, Server.db.generatePublicChatRoomsData());
			break;
		default:
			break;
		}
	}
	
}

package com.chat;

import java.io.Serializable;
import java.util.ArrayList;

public class ChatRoom implements Serializable {
	private static final long serialVersionUID = -48495501677067685L;
	
	private String chatName = null;
	private String chatHistory = null;
	
	private ArrayList<String> chatUsers = null;
	private ArrayList<String> chatModerators = null;
	private ArrayList<String> chatAdmins = null;
	
	public ChatRoom(String chatName){
		this.chatName = chatName;
	}

	public ArrayList<String> getChatModerators() {
		return chatModerators;
	}

	public void setChatModerators(ArrayList<String> chatModerators) {
		this.chatModerators = chatModerators;
	}

	public ArrayList<String> getChatAdmins() {
		return chatAdmins;
	}

	public void setChatAdmins(ArrayList<String> chatAdmins) {
		this.chatAdmins = chatAdmins;
	}

	public String getChatName() {
		return chatName;
	}

	public String getChatHistory() {
		return chatHistory;
	}

	public ArrayList<String> getChatUsers() {
		return chatUsers;
	}
	
	public void addStringToChatHistory(String s) {
		chatHistory += s;
	}

}

package com.utility;

import java.io.Serializable;
import java.util.ArrayList;

public class UserInfo implements Serializable {
	private static final long serialVersionUID = 3863758677993591803L;
	
	private ArrayList<String> friends = null;
	private ArrayList<String> savedRooms = null;
	private ArrayList<String> blocked = null;
	
	
	private String username = null;
	private String password = null;
	
	public UserInfo() {
	}
	public UserInfo(String username, String password) {
		this.password = password; this.username = username;
	}
	public ArrayList<String> getFriends() {
		return friends;
	}
	public void friends(String alias) {
		friends.add(alias);
	}
	public ArrayList<String> getSavedRooms() {
		return savedRooms;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ArrayList<String> getBlocked() {
		return blocked;
	}
	public void blockUser(String user) {
		blocked.add(user);
	}	
}

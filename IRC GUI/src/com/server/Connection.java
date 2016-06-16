package com.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import com.utility.Message;

public class Connection extends Thread {

	private ObjectInputStream input;
	private ObjectOutputStream output;

	private boolean alive = true;
	private boolean loggedIn = false;

	private String clientIP = null;
	private Socket client = null;
	
	//private String sessionID = null;
	
	private Slave slave = null;
	
	public static ArrayList<String> tasks = new ArrayList<String>();

	public Connection(Socket connection) {
		this.client = connection; //this.sessionID = sessionID;
		clientIP = client.getRemoteSocketAddress().toString();
	}

	@Override
	public void run() {
		try {
			Message message;
			configureStreams();
			greetUser();
			slave = new Slave(this);
			slave.start();
			while(alive) {
				message = (Message) input.readObject();
				System.out.println("FROM CLIENT: " + message.toString());
				slave.decode(message);
				if(message.getCommand().equals("L103")) {
					alive = false;
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Socket or streams closed, running cleanUp.");
		} finally {
			cleanUp();
		}
	}

	private void greetUser() {
		sendMessage("L104", null);
	}
	
	private void configureStreams() throws IOException {
		output = new ObjectOutputStream(client.getOutputStream());
		output.flush();
		input = new ObjectInputStream(client.getInputStream());
	}
	
	public void sendMessage(String command, String[] params) {
		Message m = new Message(command, params);
		try {
			if(!client.isClosed()) {
				output.writeObject(m);
				output.flush();
			} else {
				System.out.println("SOCKET IS DEAD");
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String command, String[] params, Object object) {
		Message m = new Message(command, params, object);
		try {
			if(!client.isClosed()) {
				output.writeObject(m);
				output.flush();
			} else {
				System.out.println("SOCKET IS DEAD");
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void cleanUp() {
		alive = false;
		//Server.db.getActiveUsers().remove(sessionID);
		//Server.setServerStatus("Connection " + sessionID + " timed out. DB: " + Server.db.getActiveUsers().keySet());
		try {
			input.close();
			output.close();
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isConnectionAlive() {
		return alive;
	}
	
	public void stopConnection() {
		alive = false;
	}

	public String getClientIP() {
		return clientIP;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

}

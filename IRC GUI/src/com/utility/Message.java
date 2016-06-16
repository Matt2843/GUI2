package com.utility;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = -2531116161088241327L;
	
	private final String COMMAND;
	private final String[] PARAMS;
	private final Object OBJECT;
	
	public Message(String COMMAND, String[] PARAMS) {
		this.COMMAND = COMMAND;
		this.PARAMS = PARAMS;
		this.OBJECT = null;
	}
	
	public Message(String COMMAND, String[] PARAMS, Object OBJECT) {
		this.COMMAND = COMMAND;
		this.PARAMS = PARAMS;
		this.OBJECT = OBJECT;
	}
	
	@Override
	public String toString() {
		String r = COMMAND;
		if(PARAMS != null) {
			for(int i = 0; i < PARAMS.length; i++) {
				r += "#" + PARAMS[i];
			}
		}
		return r;
	}

	public String getCommand() {
		return COMMAND;
	}

	public String[] getParams() {
		return PARAMS;
	}

	public Object getObject() {
		return OBJECT;
	}
	
}

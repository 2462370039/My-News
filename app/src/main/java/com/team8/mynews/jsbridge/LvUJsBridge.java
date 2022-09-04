package com.team8.mynews.jsbridge;


public interface LvUJsBridge {
	
	public void send(String data);
	public void send(String data, com.team8.mynews.jsbridge.CallBackFunction responseCallback);
	
	

}

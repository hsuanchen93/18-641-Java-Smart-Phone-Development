package com.cmu.yapeng.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer extends Thread{
	private int port=8844;
	private ServerSocket serverSocket;
	private Socket socket;
	
	private ObjectInputStream objInputStream = null;
	private ObjectOutputStream objOutputStream = null;
	
	//Constuctor
	
	public SocketServer( int port ){
		this.port=port;
	}
	
	public void run(){
		boolean testconnection=openConnection();
		if(testconnection){
			System.out.println(testconnection);
			handleSession();
			System.out.println("handle session");
		    closeConnection();
		}
		else{
			System.out.print("Connection Can not be Set Up");
		}
	}
	
	public boolean openConnection(){
		try {

			serverSocket = new ServerSocket(port);
			socket = serverSocket.accept();
			System.out.println("Test");

			objOutputStream = new ObjectOutputStream(socket.getOutputStream() ); 
			//objInputStream = new ObjectInputStream(socket.getInputStream() ); 
			System.out.println("Test2");
			
		} catch (Exception e) {
			System.out.print("cannot open socket");
			return false;
		}
		return true;
	}
	
	public void handleSession(){
		ReadData rData=new ReadData();
		String data=rData.showAll();
		try {
			System.out.println("Socket creat complete");
			objOutputStream.writeObject((Object)data);
			System.out.println("Socket creat complete");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeConnection(){
		try {
			//objInputStream.close();
			objOutputStream.close();
			socket.close();
			serverSocket.setReuseAddress(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

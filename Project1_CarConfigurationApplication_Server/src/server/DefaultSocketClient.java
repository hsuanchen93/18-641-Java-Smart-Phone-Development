/*
 * Hsuan Chen (hsuanc)
 */

package server;

import java.net.*;
import java.util.ArrayList;
import java.util.Properties;

import model.Automobile;

import java.io.*;

public class DefaultSocketClient extends Thread implements SocketClientInterface, SocketClientConstants {
    private Socket socket;
    private ServerSocket serverSocket;
    private String strHost;
    private int iPort;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;

    /* Constructor */
    public DefaultSocketClient(String strHost, int iPort) {
    	setPort(iPort);
    	setHost(strHost);
    }

    /* Constructor */
    public DefaultSocketClient(ServerSocket serverSocket, Socket socket) {
		this.serverSocket = serverSocket;
		this.socket = socket;
		setPort(4444);
		setHost(serverSocket.getInetAddress().getHostName());
	}

	/* run */
    public void run(){
    	if(openConnection()) {
    		handleSession();
    		closeSession();
    	}
    }

    /* openConnection */
    @Override
    public boolean openConnection() {
    	try {
    		//reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    		//writer = new BufferedWriter(new OutputStreamWriter (socket.getOutputStream()));
    		//out = new PrintWriter(socket.getOutputStream(), true);
    		//in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    		writer = new ObjectOutputStream(socket.getOutputStream());
    		reader = new ObjectInputStream(socket.getInputStream());
    		writer.writeObject("Connected!");
		} catch (Exception e) {
			if (DEBUG) {
				System.err.println("Unable to obtain stream to/from " + strHost);
			}
			return false;
		}
    	return true;
	}

    /* handleSession */
    @Override
    public void handleSession() {
    	if (DEBUG) {
    		System.out.println ("Handling session with " + strHost + ":" + iPort);
    	}
    	
    	//get user input type
    	String option = null;
    	try {
			option = (String) reader.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	//user creating Automobile with Properties
		if(option.equals("upload")) {
			try {
				if(DEBUG) {
					System.out.println("Parsing Properties object...");
				}
				String filename = (String) reader.readObject();
				Properties props = (Properties) reader.readObject();
				AutoServer autoServer = new BuildCarModelOptions();
				autoServer.buildAutoProperties(filename, props);
				//writer.flush();
				writer.writeObject("Automobile created successfully!");
				writer.flush();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//user configuring Automobile
		else if(option.equals("config")) {
			AutoServer autoServer = new BuildCarModelOptions();
			ArrayList<String> list = autoServer.modelList();
			try {
				writer.writeObject(list);
				writer.flush();
				String model = (String) reader.readObject();
				Automobile auto = autoServer.sendAuto(model);
				writer.writeObject(auto);
				writer.flush();
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
    
    /* closeSession */
    @Override
    public void closeSession() {
    	if(DEBUG) {
    		System.out.println("Closing session with " + strHost + ":" + iPort);
    	}
    	try {
    		//out.close();
    		//in.close();
    		writer.close();
    		reader.close();
    		socket.close();
    	} catch (IOException e) {
    		if (DEBUG) {
    			System.err.println("Error closing socket to " + strHost);
    		}
		}
	}
    
    
    /* handleInput */
    public void handleInput(String strInput) {
    	System.out.println(strInput);
    }
    
    /* setHose */
    public void setHost(String strHost) {
    	this.strHost = strHost;
    }

    /* setPort */
    public void setPort(int iPort) {
    	this.iPort = iPort;
    }
    
}
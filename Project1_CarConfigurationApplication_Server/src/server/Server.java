/*
 * Hsuan Chen (hsuanc)
 */

package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket serverSocket;
	
	/* Constructor */
	public Server() {
		//instantiate serverSocket
		serverSocket = null;
		try {
			serverSocket = new ServerSocket(4444);
		} catch (IOException e) {
			System.err.println("Could not listen on port: 4444.");
			System.exit(1);
		}
	}
	
	/* acceptClient */
	//take an incoming request and process it in its own thread
	public void acceptClient() {
		DefaultSocketClient clientSocket = null;
		try {
			while(true) {
				Socket socket = serverSocket.accept();
				clientSocket = new DefaultSocketClient(serverSocket, socket);
				clientSocket.start();
			}
		} catch(IOException e) {
			System.err.println("Accept failed.");
			System.exit(1);
		}
	}

}

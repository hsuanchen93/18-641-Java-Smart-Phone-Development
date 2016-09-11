package com.cmu.yapeng.server.driver;

import com.cmu.yapeng.server.DBConnection;
import com.cmu.yapeng.server.ReadData;
import com.cmu.yapeng.server.SocketServer;

public class Driver {
	public static void main(String[] args) {
		new DBConnection().createDB();
		String data=new ReadData().showAll();
		SocketServer server=new SocketServer(8904);
		server.start();
	}
}

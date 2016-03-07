/*
 * Hsuan Chen (hsuanc)
 */

package servlet;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import adapter.BuildAuto;
import client.DefaultSocketClient;
import client.SocketClientInterface;

@WebServlet("/modelList")
public class ModelList extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		ArrayList<String> list = null;
		
		SocketClientInterface client = new DefaultSocketClient("localhost", 4444);
		if(client.openConnection()) {
    		list = client.getAvailableModels();
    		client.closeSession();
    	}
		
		out.println("<option value=\"default\">Choose a model</option>");
		if(list!=null) {
			for(int i=0; i<list.size(); i++) {
				String model = list.get(i);
				out.println("<option value=\""+model+"\">"+model.split(":")[0]+"</option>");
			}
		}
	}
	
}

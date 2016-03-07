/*
 * Hsuan Chen (hsuanc)
 */

package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import client.DefaultSocketClient;
import client.SocketClientInterface;

@WebServlet("/optionList")
public class OptionList extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String model = request.getQueryString();
		String result = null;
		String name = "optset1";
		
		SocketClientInterface client = new DefaultSocketClient("localhost", 4444);
		if(client.openConnection()) {
    		result = client.getAvailableOptionSets(model);
    		client.closeSession();
    	}
		String[] optset = result.split("\n");
		String option = null;
		for(int i=0; i<optset.length; i++) {
			String[] opt = optset[i].split("[($):]+");
			for(int j=0; j<opt.length; ) {
				if(j==0) {
					option = opt[j];
					out.println("<tr><td>"+opt[j]+"</td><td><select id=\""+opt[j]+"\" name=\""+name+"\">");
					name = name.substring(0, name.length()-1);
					name += Integer.toString(i+2);
					j++;
				}
				else {
					out.println("<option value=\""+option+":"+opt[j]+":"+opt[j+1]+"\">"+opt[j]+"</option>");
					j+=2;
				}
			}
			out.println("</select></td></tr>");
		}
	}

}

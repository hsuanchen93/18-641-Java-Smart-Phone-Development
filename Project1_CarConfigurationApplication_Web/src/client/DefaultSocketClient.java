/*
 * Hsuan Chen (hsuanc)
 */

package client;

import java.net.*;
import java.util.ArrayList;
import java.util.Properties;

import model.Automobile;

import java.io.*;

public class DefaultSocketClient extends Thread implements SocketClientInterface, SocketClientConstants {
    private Socket socket;
    private String strHost;
    private int iPort;    
    private ObjectInputStream reader;
    private ObjectOutputStream writer;

    /* Constructor */
    public DefaultSocketClient(String strHost, int iPort) {
    	setPort(iPort);
    	setHost(strHost);
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
    		socket = new Socket(strHost, iPort);
		} catch (IOException socketError) {
			if (DEBUG) {
				System.err.println("Unable to connect to " + strHost);
			}
			return false;
		}
    	try {
    		//reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    		//writer = new BufferedWriter(new OutputStreamWriter (socket.getOutputStream()));
    		writer = new ObjectOutputStream(socket.getOutputStream());
    		reader = new ObjectInputStream(socket.getInputStream());
    	} catch (UnknownHostException e) {
            System.err.println("Don't know about host: taranis.");
            return false;
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: taranis.");
            return false;
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
    	String fromServer = null;
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int option = 0;
    	if (DEBUG) {
    		System.out.println ("Handling session with " + strHost + ":" + iPort);
    	}
    	try {
			fromServer = (String) reader.readObject();
			if(DEBUG) {
				System.out.println("Server: " + fromServer);
			}
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	//get user input
    	printMenu();
    	try{
            option = Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid option. Please enter valid number.");
            try {
				writer.writeObject("end session");
				writer.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            return;
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				writer.writeObject("end session");
				writer.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
    	
    	//option 1: upload Properties file
    	if(option==1) {
    		System.out.printf("Enter Properties file path: ");
    		String filename;
    		Properties props = null;
			try {
				filename = br.readLine();
				props = new CarModelOptionsIO().sendProperties(filename);
				if(props!=null) {
					//writer.flush();
					writer.writeObject("upload");
					writer.flush();
					writer.writeObject(filename);
					writer.flush();
					writer.writeObject(props);
					writer.flush();
					fromServer = (String) reader.readObject();
					System.out.println("Server: " + fromServer);
				}
				else {
					writer.writeObject("end session");
					writer.flush();
				}
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	//option 2: configure a car
    	else if(option==2) {
    		try {
				writer.writeObject("config");
				writer.flush();
				ArrayList<String> list = (ArrayList<String>) reader.readObject();
				ArrayList<String> newList = new ArrayList<String>();
				if(list!=null) {
					for(int i=0; i<list.size(); i++) {
						newList.add(list.get(i).split(":")[0]);
					}
				}
				SelectCarOption sel = new SelectCarOption(newList);
				sel.selectModel();
				writer.writeObject(sel.getModel());
				writer.flush();
				sel.selectAuto((Automobile) reader.readObject());
				sel.configAuto();
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	else {
    		System.out.println("No option "+option);
    		try {
				writer.writeObject("end session");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
    	}

	}
    
    /* closeSession */
    @Override
    public void closeSession() {
    	try {
    		writer.close();
    		reader.close();
    		socket.close();
    	} catch (IOException e) {
    		if (DEBUG) {
    			System.err.println("Error closing socket to " + strHost);
    		}
		}
	}
    
    /* getAvailableModels (Unit 5) */
    @Override
	public ArrayList<String> getAvailableModels() {
    	try {
			String fromServer = (String) reader.readObject();
			if(DEBUG) {
				System.out.println("Server: " + fromServer);
			}
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			writer.writeObject("config");
			writer.flush();
			ArrayList<String> list = (ArrayList<String>) reader.readObject();
			writer.writeObject(null);
			writer.flush();
			reader.readObject();
			return list;
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

    /* getAvailableOptionSets (Unit 5) */
    @Override
    public String getAvailableOptionSets(String model) {
    	try {
			String fromServer = (String) reader.readObject();
			if(DEBUG) {
				System.out.println("Server: " + fromServer);
			}
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			writer.writeObject("config");
			writer.flush();
			ArrayList<String> list = (ArrayList<String>) reader.readObject();
			writer.writeObject(model);
			writer.flush();
			Automobile auto = (Automobile) reader.readObject();
			String result = auto.printOptions();
			return result;
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
    
    /* printMenu */
    public void printMenu() {
    	System.out.printf("Menu: \n");
    	System.out.printf("1) Upload Properties file\n");
    	System.out.printf("2) Configure a car\n");
    	System.out.printf("Select option: ");
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
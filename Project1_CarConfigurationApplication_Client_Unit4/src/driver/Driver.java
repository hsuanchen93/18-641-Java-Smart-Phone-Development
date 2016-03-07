/*
 * Hsuan Chen (hsuanc)
 */

package driver;

import client.DefaultSocketClient;

public class Driver {

	public static void main(String[] args) {
		
		while(true) {
			DefaultSocketClient client = new DefaultSocketClient("localhost", 4444);
    		client.start();
    		try {
				client.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

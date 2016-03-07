/*
 * Hsuan Chen (hsuanc)
 */

package driver;

import server.Server;

public class Driver {

	public static void main(String[] args) {
		Server server = new Server();
		server.acceptClient();
    }

}

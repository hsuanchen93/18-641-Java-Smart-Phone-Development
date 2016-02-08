/*
 * Hsuan Chen, hsuanc
 */

package coinToss;

public class Coin {
	private String sideUp;
	
	/* Constructor */
	public Coin() {
		toss();
	}
	
	/* Method getSideUp */
	/* returns the value of the sideUp field */
	public String getSideUp() {
		return sideUp;
	}
	
	/* Method toss */
	/* simulates the tossing of a coin */
	public void toss() {
		double side = Math.random();
		if(side>=0.5) {
			sideUp = "heads";
		}
		else {
			sideUp = "tails";
		}
	}
	
}

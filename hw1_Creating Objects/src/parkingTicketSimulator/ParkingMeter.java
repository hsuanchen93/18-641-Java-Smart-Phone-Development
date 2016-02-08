/*
 * Hsuan Chen, hsuanc
 */

package parkingTicketSimulator;

public class ParkingMeter {
	public double minutesPurchased;
	
	/* No-Arg Constructor */
	public ParkingMeter() {
		minutesPurchased = 0;
	}
	
	/* Constructor */
	public ParkingMeter(double minutes) {
		minutesPurchased = minutes;
	}
}

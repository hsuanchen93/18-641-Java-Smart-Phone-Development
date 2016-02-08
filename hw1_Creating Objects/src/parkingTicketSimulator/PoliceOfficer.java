/*
 * Hsuan Chen, hsuanc
 */

package parkingTicketSimulator;

public class PoliceOfficer {
	public String badge;
	public String name;
	
	/* No-Arg Constructor */
	public PoliceOfficer() {
		name = null;
		badge = null;
	}
	
	/* Constructor */
	public PoliceOfficer(String name, String badge) {
		this.name = name;
		this.badge = badge;
	}

	/* Method issueTicket */
	/* returns ticket if car time has expired */
	public ParkingTicket issueTicket(ParkedCar car, ParkingMeter meter) {
		double extraTime;
		int extraHrs;
		if(car.getMinutes() > meter.minutesPurchased) {
			extraTime = car.getMinutes() - meter.minutesPurchased;
			extraHrs = (int)Math.ceil(extraTime/60.0);
			ParkingTicket ticket = new ParkingTicket(25+10*(extraHrs-1));
			return ticket;
		}
		else {
			return null;
		}
	}
	
}
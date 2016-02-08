/*
 * Hsuan Chen, hsuanc
 */

package parkingTicketSimulator;

import java.util.List;

public class ParkingTicket {
	private int fine;
	
	/* No-Arg Constructor */
	public ParkingTicket() {
		fine = 0;
	}
	
	/* Constructor */
	public ParkingTicket(int fine) {
		this.fine = fine;
	}
	
	/* Method reportCar */
	/* reports the make, model, color and license of an illegally parked car */
	void reportCar(List<String> lines, ParkedCar car) {
		lines.add("Information of illegally parked car:");
		lines.add("Make: " + car.getMake());
		lines.add("Model: " + car.getModel());
		lines.add("Color: " + car.getColor());
		lines.add("License: " + car.getLicense());
	}
	
	/* Method reportFine */
	/* reports the fine */
	void reportFine(List<String> lines) {
		lines.add("Fine: " + fine);
	}
	
	/* Method reportPolice */
	/* reports the name and badge of police officer issuing the ticket */
	void reportPolice(List<String> lines, PoliceOfficer officer) {
		lines.add("Police officer issuing the ticket:");
		lines.add("Name: " + officer.name);
		lines.add("Badge: " + officer.badge);
	}
}

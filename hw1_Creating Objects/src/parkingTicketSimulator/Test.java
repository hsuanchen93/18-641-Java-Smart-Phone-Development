/*
 * Hsuan Chen, hsuanc
 */

package parkingTicketSimulator;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Test {

	/* Main */
	/* simulates and tests varies situations */
	public static void main(String[] args) {
		int NUM_TEST = 6;
		ParkingTicket[] ticket = new ParkingTicket[NUM_TEST];
		
		//create output file
		Path file = Paths.get("ParkingTicketOutput.txt");
		Charset charset = Charset.forName("UTF-8");
		List<String> lines = new ArrayList<String>();
		
		//initialize objects
		ParkedCar car = new ParkedCar("Honda","Accord","Silver","F12345",39.9);
		ParkingMeter meter = new ParkingMeter(40);
		PoliceOfficer officer = new PoliceOfficer("Abby","AAA789");
		
		//case 1: parked vehicle is within the parking time purchased
		ticket[0] = officer.issueTicket(car, meter);
		
		//case 2: parked vehicle is just in the parking time purchased
		//expected result: no ticket
		car.setMinutes(45);
		meter.minutesPurchased = 45;
		ticket[1] = officer.issueTicket(car, meter);
		
		//case 3: ticketing under 1 hour
		//expected result: $25 fine
		car.setMinutes(50.1);
		meter.minutesPurchased = 50;
		ticket[2] = officer.issueTicket(car, meter);
		
		//case 4: ticketing just at 1 hour
		//expected result: $25 fine
		car.setMinutes(80);
		meter.minutesPurchased = 20;
		ticket[3] = officer.issueTicket(car, meter);
		
		//case 5: ticketing more than 1 hour
		//expected result: $35 fine
		car.setMinutes(90);
		meter.minutesPurchased = 10;
		ticket[4] = officer.issueTicket(car, meter);
		
		//case 6: ticketing more than 2 hours
		//expected result: $45 fine
		car.setMinutes(180.1);
		meter.minutesPurchased = 60;
		ticket[5] = officer.issueTicket(car, meter);
		
		//report results
		for(int i=0; i<NUM_TEST; i++) {
			lines.add("case " + (i+1) + ":");
			if(ticket[i]==null) {
				lines.add("No Parking Ticket Issued\n");
			}
			else {
				ticket[i].reportFine(lines);
				ticket[i].reportCar(lines, car);
				ticket[i].reportPolice(lines, officer);
				lines.add("");
			}
		}
		
		//write result to output file
		try {
			Files.write(file, lines, charset);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

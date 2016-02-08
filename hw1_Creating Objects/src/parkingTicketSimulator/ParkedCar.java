/*
 * Hsuan Chen, hsuanc
 */

package parkingTicketSimulator;

public class ParkedCar {
	private String color;
	private String license;
	private String make;
	private double minutes;
	private String model;
	
	/* No-Arg Constructor */
	public ParkedCar() {
		make = null;
		model = null;
		color = null;
		license = null;
		minutes = 0;
	}
	
	/* Constructor */
	public ParkedCar(String make, String model, String color, 
					 String license, double minutes) {
		this.make = make;
		this.model = model;
		this.color = color;
		this.license = license;
		this.minutes = minutes;
	}
	
	/* Getters */
	public String getColor() {
		return color;
	}
	public String getLicense() {
		return license;
	}
	public String getMake() {
		return make;
	}
	public double getMinutes() {
		return minutes;
	}
	public String getModel() {
		return model;
	}
	
	/* Setters */
	public void setColor(String color) {
		this.color = color;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public void setMinutes(double minutes) {
		this.minutes = minutes;
	}
	public void setModel(String model) {
		this.model = model;
	}

}

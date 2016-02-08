/*
 * Hsuan Chen (hsuanc)
 */

package model;

public class Statistics implements Info {
	private float[] avgScores = new float[5];
	private int[] highScores = new int[5];
	private int[] lowScores = new int[5];
	
	/* No-Arg Constructor */
	public Statistics() {
		for(int i=0; i<5; i++) {
			avgScores[i] = 0;
			highScores[i] = 0;
			lowScores[i] = 100;
		}
	}
	
	/* Method findAvg*/
	/* find average score for each quiz and store it in array avgScores */
	public void findAvg(Student[] a){
		//add up all the quiz scores
		int numStudent = 0;
		for(int i=0; i<5; i++) {
			numStudent = 0;
			for(int j=0; j<a.length; j++) {
				if(a[j]!=null) {
					avgScores[i] += a[j].getScores()[i];
					numStudent++;
				}
				else {
					break;
				}
			}
		}
		//get the average
		for(int k=0; k<5; k++) {
			avgScores[k] = avgScores[k]/(float)numStudent;
		}
	}
	
	/* Method findHigh */
	/* find the highest score and store it in an array highScores */
	public void findHigh(Student[] a){
		for(int i=0; i<5; i++) {
			for(int j=0; j<a.length; j++) {
				if(a[j]!=null) {
					if(highScores[i] < a[j].getScores()[i]) {
						highScores[i] = a[j].getScores()[i];
					}
				}
				else {
					break;
				}
			}
		}	
	}
	
	/* Method findLow */
	/* find the lowest score and store it in an array lowScores */
	public void findLow(Student[] a){
		for(int i=0; i<5; i++) {
			for(int j=0; j<a.length; j++) {
				if(a[j]!=null) {
					if(lowScores[i] > a[j].getScores()[i]) {
						lowScores[i] = a[j].getScores()[i];
					}
				}
				else {
					break;
				}
			}
		}
	}
	
	/* Method printInfo */
	/* output High Score, Low Score, and Average Score */
	@Override
	public void printInfo() {
		System.out.printf("%-11s%8d%8d%8d%8d%8d\n", "High Score", 
				highScores[0], highScores[1], highScores[2], highScores[3], 
																highScores[4]);
		
		System.out.printf("%-11s%8d%8d%8d%8d%8d\n", "Low Score", lowScores[0], 
				lowScores[1], lowScores[2], lowScores[3], lowScores[4]);
		
		System.out.printf("%-11s%8.2f%8.2f%8.2f%8.2f%8.2f\n", "Average", 
				avgScores[0], avgScores[1], avgScores[2], avgScores[3], 
																avgScores[4]);
		
	}
}

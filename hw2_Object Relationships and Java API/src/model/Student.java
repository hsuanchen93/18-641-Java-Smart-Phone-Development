/*
 * Hsuan Chen (hsuanc)
 */

package model;

public class Student implements Info {
	private int scores[] = new int[5];
	private int SID;
	
	/* No-Arg Constructor */
	public Student() {
		scores = null;
		SID = 0;
	}
	
	/* Getters */
	public int[] getScores() {
		return scores;
	}
	public int getSID() {
		return SID;
	}
	
	/* Method printInfo */
	/* output SID and all scores for quizzes */
	@Override
	public void printInfo() {
		System.out.printf("%-11s%8d%8d%8d%8d%8d\n", Integer.toString(SID), 
				scores[0], scores[1], scores[2], scores[3], scores[4]);
	}

	
	/* Setters */
	public void setScores(int[] scores) {
		this.scores = scores;
	}
	public void setSID(int SID) {
		this.SID = SID;
	}
	
}
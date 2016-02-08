/*
 * Hsuan Chen, hsuanc
 */

package coinToss;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Simulation {
	public static int heads;
	public static int tails;
	
	/* Method init */
	/* initialize the counts */
	public static void init(Coin c, List<String> lines) {
		heads = 0;
		tails = 0;
		lines.add("inital side up: " + c.getSideUp());
		track(c.getSideUp(), lines);
	}
	
	/* Main */
	/* demonstrate the simulation */
	public static void main(String[] args) {
		//create output file
		Path file = Paths.get("CoinTossOutput.txt");
		Charset charset = Charset.forName("UTF-8");
		List<String> lines = new ArrayList<String>();
		
		//case 1: toss the coin 0 times
		lines.add("case 1: toss the coin 0 times");
		Coin c1 = new Coin();
		init(c1, lines);
		doTrial(0, c1, lines);
		
		//case 2: toss the coin 5 times
		lines.add("case 2: toss the coin 5 times");
		Coin c2 = new Coin();
		init(c2, lines);
		doTrial(5, c2, lines);
		
		//case 3: toss the coin 10 times
		lines.add("case 3: toss the coin 10 times");
		Coin c3 = new Coin();
		init(c3, lines);
		doTrial(10, c3, lines);
		
		//case 4: toss the coin 20 times
		lines.add("case 4: toss the coin 20 times");
		Coin c4 = new Coin();
		init(c4, lines);
		doTrial(20, c4, lines);
		
		//write result to output file
		try {
			Files.write(file, lines, charset);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* Method track */
	/* keeps track of total number of heads and tails */
	public static void track(String side, List<String> lines) {
		if(side=="heads") {
			heads += 1;
		}
		else if(side=="tails") {
			tails += 1;
		}
		else {
			lines.add("Error: invalid sideUp");
		}
	}
	
	/* Method trial */
	/* tosses the coin x number of times */
	public static void doTrial(int x, Coin c, List<String> lines) {
		for(int i=0; i<x; i++) {
			c.toss();
			lines.add("toss " + (i+1) + ": " + c.getSideUp());
			track(c.getSideUp(), lines);
		}
		lines.add("total number of heads: " + heads);
		lines.add("total number of tails: " + tails);
		lines.add("");
	}
	
}

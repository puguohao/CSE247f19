package studio1;

import timing.ExecuteAlgorithm;
import timing.Ticker;
import timing.quiet.QuietAlgorithm;
import timing.utils.GenSizes;
import timing.utils.IntArrayGenerator;


public class ProbD extends QuietAlgorithm {

	protected Ticker ticker;
	protected long   sum;

	public ProbD() {

	}

	/**
	 * Begin with an array of two elements.
	 */
	@Override
	public void reset(Ticker ticker) {
		this.ticker = ticker;
	}

	/**
	 * As a "quiet" algorithm, all we care about is the
	 * parameter n.
	 */
	@Override
	public void run() {
		int j = 1;
		while (j <= n) {
			int k = 1;
			while (k <= j*j) {
				//
				// Here
				ticker.tick();
				//				
				k = k + 1;
			}
			j = j + 1;
		}
	}
	//平方和相加公式n(n+1)(2n+1)/6
	//final tick result: n(n+1)(2n+1)/6
	public String toString() {
		return "Problem D";
	}

	public static void main(String[] args) {
		GenSizes sizes = GenSizes.arithmetic(1, 100, 1);
		ExecuteAlgorithm.timeAlgorithm(
				"ProbD", 
				"studio1.ProbD", 
				new IntArrayGenerator(), 
				sizes
				);	
	}

}

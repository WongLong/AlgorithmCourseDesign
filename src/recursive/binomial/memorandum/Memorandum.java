package recursive.binomial.memorandum;

import java.math.BigInteger;

public class Memorandum {
	private static BigInteger[][] memo;

	public static BigInteger binomial(int k, int n) {
		if(k > n / 2) {
			k = n - k;
		}
		
		memo = new BigInteger[n + 1][k + 1];

		return getBinomial(k, n);
	}
	
	private static BigInteger getBinomial(int k, int n) {
		if(memo[n][k] == null) {
			if (k == 0 || k == n) {
				memo[n][k] = new BigInteger("1");
			} else {
				memo[n][k] = getBinomial(k - 1, n - 1).add(getBinomial(k, n - 1));
			}
		}
		
		return memo[n][k];
	}
}

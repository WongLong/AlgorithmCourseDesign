package recursive.binomial.recursion;

import java.math.BigInteger;

public class Recursion {
	public static BigInteger binomial(int k, int n) {
		if(k > n / 2) {
			k = n - k;
		}
		
		if (k == 0 || k == n) {
			return new BigInteger("1");
		} else {
			return binomial(k - 1, n - 1).add(binomial(k, n - 1));
		}
	}
}

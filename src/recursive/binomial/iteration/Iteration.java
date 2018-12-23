package recursive.binomial.iteration;

import java.math.BigInteger;

public class Iteration {
	public static BigInteger binomial(int k, int n) {
		if(k > n / 2) {
			k = n - k;
		}
		
		BigInteger[] temp = new BigInteger[k + 1];

		for (int i = 0; i <= n; i++) {		
			BigInteger lastValue = new BigInteger("1");
			
			for (int j = 0; j <= i; j++) {				
				if(j > k) break;
				
				if (j == 0 || j == i) {
					temp[j] = new BigInteger("1");
				}else {
					BigInteger currentValue = temp[j];
					temp[j] = temp[j].add(lastValue);
					lastValue = currentValue;
				}
			}
		}

		return temp[k];
	}
}

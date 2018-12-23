package recursive.binomial;

import recursive.binomial.iteration.Iteration;
import recursive.binomial.memorandum.Memorandum;
import recursive.binomial.recursion.Recursion;

public class bootstrap {
	public static void main(String[] args) {
		int k = 10;
		int n = 30;
		
		long time = System.currentTimeMillis();
		System.out.println(Memorandum.binomial(k, n));
		System.out.println("备忘录耗时：" + (System.currentTimeMillis() - time) + "ms");
		
		time = System.currentTimeMillis();
		System.out.println(Iteration.binomial(k, n));
		System.out.println("迭代算法耗时：" + (System.currentTimeMillis() - time) + "ms");
		
		time = System.currentTimeMillis();
		System.out.println(Recursion.binomial(k, n));
		System.out.println("递归算法耗时：" + (System.currentTimeMillis() - time) + "ms");
	}
}

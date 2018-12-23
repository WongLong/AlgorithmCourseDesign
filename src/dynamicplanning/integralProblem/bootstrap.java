package dynamicplanning.integralProblem;

import dynamicplanning.integralProblem.entity.Merchandise;

public class bootstrap {
	private static int[][] table;

	public static void main(String[] args) {
		int numberOfMerchandise = 20;
		int totalIntegral = 100000;

		System.out.println("��Ʒ������ " + numberOfMerchandise + "\t ӵ�л��֣�" + totalIntegral);
		System.out.println();
		Merchandise[] merchandises = createMerchandise(numberOfMerchandise, totalIntegral);
		System.out.println("������ɵ���Ʒ�б����£�");
		printMerchandise(merchandises);
		process(merchandises, totalIntegral);
	}

	private static void process(Merchandise[] merchandises, int totalIntegral) {
		table = new int[merchandises.length + 1][totalIntegral + 1];

		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				if (i == 0 || j == 0) {
					table[i][j] = 0;
				} else {
					if (j >= merchandises[i - 1].getIntegral()) {
						table[i][j] = Math.max(
								table[i - 1][j - merchandises[i - 1].getIntegral()] + merchandises[i - 1].getValue(),
								table[i - 1][j]);
					} else {
						table[i][j] = table[i - 1][j];
					}
				}
			}
		}
		int expendIntegral = printRes(merchandises.length, totalIntegral, merchandises);
		System.out.println();
		System.out.println("�����Ļ��֣�" + expendIntegral);
		System.out.println("���һ�����Ʒ�ܼ�ֵ��" + table[merchandises.length][totalIntegral]);
	}

	private static Merchandise[] createMerchandise(int numberOfMerchandise, int totalIntegral) {
		Merchandise[] merchandises = new Merchandise[numberOfMerchandise];

		for (int i = 0; i < numberOfMerchandise; i++) {
			int value = (int) (Math.random() * totalIntegral / 1000.0 * 9 + totalIntegral / 100);
			int integral = (int) (Math.random() * value + totalIntegral / numberOfMerchandise * 2);
			Merchandise m = new Merchandise(value, integral);
			merchandises[i] = m;
		}

		return merchandises;
	}

	private static void printMerchandise(Merchandise[] merchandises) {
		for (int i = 0; i < merchandises.length; i++) {
			System.out.println("��Ʒ" + (i + 1) + "\t�������  = " + merchandises[i].getIntegral() + ", \t�۸� = "
					+ merchandises[i].getValue() + " ");
		}
		System.out.println();
	}

	private static int printRes(int index, int integral, Merchandise[] merchandises) {
		if (index > 0) {
			if (table[index][integral] == table[index - 1][integral]) {
				return printRes(index - 1, integral, merchandises);
			}else {
				int expend = printRes(index - 1, integral - merchandises[index - 1].getIntegral(), merchandises);
				System.out.print("��" + index + "����Ʒ\t");
				
				return merchandises[index - 1].getIntegral() + expend;
			}
		}
		
		return 0;
	}
}

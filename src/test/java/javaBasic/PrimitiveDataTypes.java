package javaBasic;

public class PrimitiveDataTypes {

    public static void main(String[] args) {

        // 0) применить несколько арифметических операций ( + , -, * , /) над двумя примитивами типа int

        int a = 10;
        int b = 2;

        System.out.println("Результат сложения : " + (a + b));
        System.out.println("Результат вычитания : " + (a - b));
        System.out.println("Результат умножения : " + (a * b));
        System.out.println("Результат деления : " + (a / b));

        // 1) применить несколько арифметических операций над int и double в одном выражении

        int c = 5;
        double d = 2.5;

        double result1 = c + d * 2;
        System.out.println(result1);
        int result2 = (int) (c / d);
        System.out.println(result2);

        // 2) применить несколько логических операций ( < , >, >=, <= )
        if (a > b) {
            System.out.println("a > b");
        } else if (a < b) {
            System.out.println("a < b");
        } else if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a <= b");
        }

        // 4) получить переполнение при арифметической операции

        // Переполнение int (максимальное значение: 2_147_483_647)
        int maxInt = Integer.MAX_VALUE;
        int overflowInt = maxInt + 1;  // Станет -2_147_483_648 (минимальное int)

        System.out.println("Max int: " + maxInt);
        System.out.println("After overflow: " + overflowInt);

        // Переполнение long (максимальное значение: 9_223_372_036_854_775_807)
        long maxLong = Long.MAX_VALUE;
        long overflowLong = maxLong + 1;  // Станет -9_223_372_036_854_775_808

        System.out.println("Max long: " + maxLong);
        System.out.println("After overflow: " + overflowLong);

    }

}
package ua.khpi.oop.Rubiezhyn02;

public class main {
    public static void main(String[] args) {

        int sum1 = 0;
        int sum2 = 0;

        System.out.print("Текущее число | Разбитое число №1 | Разбитое число №2 ");
        System.out.println("| Сумма числа №1 | Сумма числа №2 | Совпадения");

        findAndPrintResult(sum1,sum2);

    }

    private static void findAndPrintResult(int sum1, int sum2){
        for (int i = 0; i < 10; i++) {
            int mRandomNumber = (int)(100000 + Math.random() * 900000);

            int mNumber = mRandomNumber / 1000;
            int mNumber2 = mRandomNumber % 1000;

            sum1 = getSumFromNumber(mNumber);
            sum2 = getSumFromNumber(mNumber2);

            System.out.printf("%13d | %18d | %18d ", mRandomNumber, mNumber, mNumber2);
            System.out.printf("| %14d | %15d | ", sum1, sum2);

            if (sum1 == sum2) {
                System.out.println("Присутствует");
            } else {
                System.out.println("Отсутствует");
            }
        }
    }

    private static int getSumFromNumber(int number) {
        int summ = 0;

        summ += number / 100;
        summ += (number / 10) % 10;
        summ += number % 10;

        return summ;
    }

}

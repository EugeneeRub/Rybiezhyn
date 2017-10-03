package ua.khpi.oop.Rubiezhyn01;

/**
 * Разработал Рубежин Евгений
 * Дата создания 06.09.2017
 * */

public class CMain {

    public static void main(String[] args) {
        CTask3 task3 = new CTask3();
        task3.findCountOfBinaryNumbers(Integer.toBinaryString(9));

        CTask2 task2 = new CTask2();
        task2.findCountOfNumbers(29561317);
        try {
            CTask1 task1 = new CTask1();
            task1.firstUnderTask(15315);
            task1.thirdUnderTask(380501788813L);
            task1.fourthUnderTask(380501788813L);
            int number = task1.fifthUnderTask(16);
            task1.sixUnderTask(number);
        } catch (myExeption ex) {
            ex.printExeption();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}

class myExeption extends Exception {
    void printExeption() {
        System.out.println("Присутствуют нули!!!");
    }
}

//Використовуючи двійковий запис цілочисельного значення кожної змінної підрахувати кількість одиниць.
class CTask3 {

    void findCountOfBinaryNumbers(String number){
        int countOfNumbers = 0;
        for (int i = 0; i < number.length(); i++) {
            if(((char)number.charAt(i)) == '1') countOfNumbers++;
        }
        //System.out.println("Количество 1-чек: " + countOfNumbers);
    }
}

//Використовуючи десятковий запис цілочисельного значення кожної змінної знайти і підрахувати кількість парних і непарних цифр.
class CTask2 {

    void findCountOfNumbers(int number){
        String str = Integer.toString(number);
        int counterForParnie = 0;
        int counterForNonParnie = 0;
        for (int i = 0; i < str.length(); i++) {
            int newNumber = ((char)str.charAt(i)) - 48;
            if((newNumber % 2) == 0) counterForParnie++;
            else counterForNonParnie++;
        }
        //System.out.println("Кол-во парных чисел = " + counterForParnie);
        //System.out.println("Кол-во непарных чисел = " + counterForNonParnie);
    }

}

class CTask1 {

    //число, що відповідає номеру залікової книжки за допомогою шістнадцяткового літералу;
    void firstUnderTask(int numberOfRecordBook) {
        String result = "0x" + Integer.toHexString(numberOfRecordBook);
        //System.out.println("Ваш номер зачетной книги в 16-ной системе: 0x" + numberIn16System);
    }

    //число, що відповідає номеру мобільного телефона (починаючи з 380...) за допомогою десяткового літералу;
    void secondUnderTask() {
        long phoneNumber = 380501788813L;
    }

    //число, яке складається з останніх двох ненульових цифр номера мобільного телефону за допомогою двійкового літералу;
    void thirdUnderTask(long numberOfPhone) throws myExeption {
        String str = Long.toString(numberOfPhone);
        int size = str.length();
        int result;
        if (size < 3) {
            //System.out.println("Невозможно форматирование!");
        } else {
            long number = numberOfPhone%100;
            if(number/10 == 0 && number % 10 == 0){
                throw new myExeption();
            }
            result = Integer.parseInt(Integer.toBinaryString((int) number));
            System.out.println(result);
        }

    }

    //число, яке складається з останніх чотирьох ненульових цифр номера мобільного телефону за допомогою вісімкового літералу;
    void fourthUnderTask(long numberOfPhone) throws myExeption {
        String str = Long.toString(numberOfPhone);
        int size = str.length();
        if (size < 5) {
            //System.out.println("Невозможно форматирование!");
        } else {
            long number = numberOfPhone % 10000;
            if(number/10 == 0 && number % 10 == 0 && (number/100)%10 == 0 && (number%100)/10 == 0){
                throw new myExeption();
            }
            long result = Long.parseLong(Integer.toOctalString((int) number));
            System.out.println(result);
        }

    }

    //визначити збільшене на одиницю значення залишку від ділення на 26 зменшеного на одиницю номера студента в журналі групи;
    int fifthUnderTask(int numberInMagazine) throws myExeption {
        if (numberInMagazine > 26) System.out.println("Перебор!!!");
        int result = ((26 - ((26 / (numberInMagazine - 1)) * (numberInMagazine - 1))) + 1);
        //System.out.println("Результат: " + result);
        return result;
    }

    //символ англійського алфавіту в верхньому регістрі, номер якого відповідає знайденому раніше значенню.
    void sixUnderTask(int number) throws myExeption {
        if(number < 26) {
            char ch = (char) (64 + number);
            System.out.println(ch);
        }
    }
}

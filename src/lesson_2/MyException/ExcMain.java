package lesson_2.MyException;

import lesson_2.MyException.CustomException.*;

public class ExcMain {
    public static void main(String[] args) {
        run();
    }

    private static void run(){
        int sum = 0;
        boolean error = true;

        String[][] items = {{"43", "12", "54", "9"}, {"12", "2", "76", "3"}, {"31", "36", "7", "83"}, {"2", "17", "63", "59"}};

        try {
            sum = processing(items);
            error = false;
        }
        catch (MyArraySizeException ex){
            System.out.println(String.format("Ошибка в размере массива: %s", ex.getMessage()));
            ex.getStackTrace();
        }
        catch (MyArrayDataException ex){
            System.out.println(String.format("Ошибка преобразования элемента массива: %s", ex.getMessage()));
            ex.getStackTrace();
        }

        if(!error){
            System.out.println(String.format("Сумма элементов массива = %,d", sum));
        }
    }

    private static int processing(String[][] items) {
        checkArrayLength(items.length);

        int sum = 0;

        for(int i = 0; i < items.length; i++){
            String[] item = items[i];

            checkArrayLength(item.length);

            for (int j = 0; j < item.length; j++){
                try{
                    int number = Integer.parseInt( item[j] );
                    sum += number;
                }
                catch (NumberFormatException ex){
                    throw new MyArrayDataException(String.format( "[%d][%d]", i, j ));
                }
            }
        }

        return sum;
    }

    private static void checkArrayLength(int length){
        if(length != 4){
            throw new MyArraySizeException();
        }
    }
}




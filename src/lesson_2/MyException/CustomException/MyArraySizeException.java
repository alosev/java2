package lesson_2.MyException.CustomException;

public class MyArraySizeException extends RuntimeException{
    public MyArraySizeException(String message) {
        super( message );
    }

    public MyArraySizeException() {
        this("Размерность массива неадекватная!");
    }
}

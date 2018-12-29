package lesson_2.MyException.CustomException;

public class MyArrayDataException extends RuntimeException {
    public MyArrayDataException(String message) {
        super( message );
    }

    public MyArrayDataException() {
        this("Ошибка преобразования");
    }
}

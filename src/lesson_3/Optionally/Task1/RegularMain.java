package lesson_3.Optionally.Task1;

public class RegularMain {
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        String password = "34Yjf3l2";

        PasswordHelper passwordHelper = new PasswordHelper();

        //включает выброс исключения
        //passwordHelper.throwException = true;

        boolean result = passwordHelper.validPassword(password);

        System.out.println(result);
    }

}



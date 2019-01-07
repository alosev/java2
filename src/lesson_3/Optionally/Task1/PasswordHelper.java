package lesson_3.Optionally.Task1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordHelper {
    public static final int minCharCount = 8;

    public boolean throwException = false;

    public boolean validPassword(String password) {

        return checkCountChars(password)
                && checkNumber(password)
                && checkLowercase(password)
                && checkUppercase(password)
                && checkWhitespace(password)
                && checkSpecialChars(password);
    }

    //1. Пароль должен быть не менее х символов.
    public boolean checkCountChars(String password) {
        Pattern pattern = Pattern.compile(".{" + minCharCount + ",}");
        Matcher matcher = pattern.matcher(password);
        boolean result = matcher.find();
        if(throwException && !result){
            throw new PasswordValidationException("Пароль должен быть не менее " + minCharCount + " символов");
        }
        return result;
    }

    //2. В пароле должно быть число
    public boolean checkNumber(String password) {
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(password);
        boolean result = matcher.find();
        if(throwException && !result){
            throw new PasswordValidationException("В пароле должно быть число");
        }
        return result;
    }

    //3. В пароле должна быть хотя бы 1 строчная буква
    public boolean checkLowercase(String password) {
        Pattern pattern = Pattern.compile("[a-zа-я]+");
        Matcher matcher = pattern.matcher(password);
        boolean result = matcher.find();
        if(throwException && !result){
            throw new PasswordValidationException("В пароле должна быть хотя бы 1 строчная буква");
        }
        return result;
    }

    //4. В пароле должна быть хотя бы 1 заглавная буква
    public boolean checkUppercase(String password) {
        Pattern pattern = Pattern.compile("[A-ZА-Я]+");
        Matcher matcher = pattern.matcher(password);
        boolean result = matcher.find();
        if(throwException && !result){
            throw new PasswordValidationException("В пароле должна быть хотя бы 1 заглавная буква");
        }
        return result;
    }

    //5. Не должно быть пробелов
    public boolean checkWhitespace(String password) {
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(password);
        boolean result = !matcher.find();
        if(throwException && !result){
            throw new PasswordValidationException("В пароле не должно быть пробелов");
        }
        return result;
    }

    //6. Должен быть спец. символ
    public boolean checkSpecialChars(String password) {
        Pattern pattern = Pattern.compile("[!@#$%^&*?]+");
        Matcher matcher = pattern.matcher(password);
        boolean result = matcher.find();
        if(throwException && !result){
            throw new PasswordValidationException("В пароле должен быть спец. символ");
        }
        return result;
    }
}

class PasswordValidationException extends RuntimeException{
    public PasswordValidationException(String message) {
        super(message);
    }
}

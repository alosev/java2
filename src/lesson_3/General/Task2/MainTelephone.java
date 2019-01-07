package lesson_3.General.Task2;

import java.util.ArrayList;

public class MainTelephone {
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        TelephoneDirectory dir = new TelephoneDirectory();

        dir.add("Василий", "79261112233");
        dir.add("Егор", "78926667711");
        dir.add("Иван", "79991237621");
        dir.add("Василий", "79263332211");
        dir.add("Андрей", "78116782211");
        dir.add("Андрей", "77822331212");

        dir.printInfo();

        ArrayList<String> phones = dir.get("Иван");
        System.out.println("Телефоны Ивана:" + phones);

        phones = dir.get("Андрей");
        System.out.println("Телефоны Андрея:" + phones);
    }
}


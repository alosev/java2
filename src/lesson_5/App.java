package lesson_5;

import lesson_5.model.ArrayHelper;

public class App {
    public static void main(String[] args) throws InterruptedException {
        run();
    }

    private static void run() throws InterruptedException {
        int size = 10_000_000;

        ArrayHelper helper = ArrayHelper.initArray(size);

        long a = System.currentTimeMillis();
        helper.processing();
        System.out.println(System.currentTimeMillis() - a);

        long b = System.currentTimeMillis();
        helper.processingSplitWithoutThread(4);
        System.out.println(System.currentTimeMillis() - b);

        long c = System.currentTimeMillis();
        helper.processingSplitWithThread(4);
        System.out.println(System.currentTimeMillis() - c);
    }
}

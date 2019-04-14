package lesson_3.General.Task1;

import java.util.*;

public class MainWords {
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        String[] words = initArray();
        System.out.println("Начальный массив:");
        System.out.println(Arrays.asList(words));

        HashSet<String> wordsUnique = new HashSet<>(Arrays.asList(words));
        System.out.println("Коллекция уникальных значений:");
        System.out.println(wordsUnique);

        // не оптимально, просто захотелось посмотреть что такое итератор
        ArrayList<String> wordsList = new ArrayList<>(Arrays.asList(words));
        Iterator<String> wordsIterator = wordsList.iterator();
        HashMap<String, Integer> wordsCount = countDuplicate(wordsIterator);
        System.out.println("Кол-во повторений:");
        System.out.println(wordsCount);
    }

    private static HashMap<String, Integer> countDuplicate(Iterator<String> wordsIterator) {
        HashMap<String, Integer> wordsCount = new HashMap<>();

        while (wordsIterator.hasNext()) {
            String word = wordsIterator.next();
            Integer count = wordsCount.getOrDefault(word, 0);
            wordsCount.put(word, ++count);
        }

        return wordsCount;
    }

    private static String[] initArray() {
        return new String[]{
                "Scala",
                "Gradle",
                "Java",
                "Uganda",
                "Russia",
                "Germany",
                "Somali",
                "Scala",
                "Music",
                "Java",
                "Cat",
                "Dog",
                "Germany",
                "Java"
        };
    }
}

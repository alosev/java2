package lesson_5.model;

import java.util.ArrayList;

public class ArrayHelper{
    private float[] elements;

    // скрываем конструктор, т.к. создавать объект будет статический метод
    private ArrayHelper(int size){
        elements = new float[size];

        for (int i = 0; i < elements.length; i++) {
            elements[i] = 1f;
        }
    }

    // долго создавать объект через конструктор некомильфо, поэтому так
    public static ArrayHelper initArray(int size) {
        return new ArrayHelper(size);
    }

    public void processing(){
        processingElements(elements);
    }

    public void processingSplitWithoutThread(int countGroup){
        float[][] groups = splitElements(elements, countGroup);

        for (int i = 0; i < groups.length; i++) {
            processingElements(groups[i]);
        }

        elements = mergeElements(groups);
    }

    public void processingSplitWithThread(int countGroup) throws InterruptedException {
        float[][] groups = splitElements(elements, countGroup);

        ArrayList<Thread> threads = new ArrayList<>(countGroup);
        for (int i = 0; i < groups.length; i++) {
            float[] elementsForProcessing = groups[i];
            Thread t = new Thread(() -> processingElements(elementsForProcessing));
            t.start();
            threads.add(t);
        }

        // ожидаем завершения обработки всех групп
        for (Thread thread : threads) {
            thread.join();
        }

        elements = mergeElements(groups);
    }

    private void processingElements(float[] arr){
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    private float[][] splitElements(float[] elements, int countGroup ){
        int h = elements.length / countGroup;
        float[][] groups = new float[countGroup][];

        float[] groupElement = new float[h];
        int processingElements = 0;
        int processingGroup = 0;

        for (int i = 0; i < elements.length; i++) {
            groupElement[processingElements] = elements[i];
            processingElements++;

            if(processingElements >= h){
                groups[processingGroup] = groupElement;
                processingGroup++;
                processingElements = 0;

                // если группа последняя, то запихиваем туда все оставшиеся элементы
                if(processingGroup >= (countGroup - 1)){
                    h = Math.max(elements.length - (h * processingGroup), 0);
                }

                groupElement = new float[h];
            }
        }

        return groups;
    }

    private float[] mergeElements(float[][] groupElements){
        int countElements = 0;

        // не нравится мне этот лишний обход НЕМАЛЕНЬКОГО массива, но ничего не придумал
        for (int i = 0; i < groupElements.length; i++) {
            countElements+= groupElements[i].length;
        }

        float[] elements = new float[countElements];

        for (int i = 0; i < groupElements.length; i++) {
            for (int j = 0; j < groupElements[i].length; j++) {
                countElements--;
                elements[countElements]= groupElements[i][j];
            }
        }

        return elements;
    }
}

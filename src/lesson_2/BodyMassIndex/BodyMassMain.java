package lesson_2.BodyMassIndex;

import java.util.Arrays;

public class BodyMassMain {
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        float[][] data = getData();

        BMICategory[] result = new BMICategory[data.length];

        for (int i = 0; i < data.length; i++) {
            float[] item = data[i];

            if(item.length != 2){
                throw new IllegalArgumentException( "Неверный формат входных данных" );
            }

            result[i] = measure(item[0], item[1]);
        }

        System.out.println( Arrays.asList( result ));
    }

    private static BMICategory measure(float weight, float height){
        float index = (float) (weight / Math.pow(height, 2));

        return BMICategory.FromBodyMassIndex( index );
    }

    private static float[][] getData() {
        return new float[][]{
                {118, 2.05f},
                {106, 1.77f},
                {87, 1.83f},
                {45, 1.12f},
                {70, 1.87f},
                {54, 1.57f},
                {105, 1.76f},
                {50, 1.96f},
                {114, 1.76f},
                {72, 2.45f},
                {53, 2.10f},
                {66, 2.25f},
                {54, 1.50f},
                {95, 1.62f},
                {86, 1.72f},
                {62, 1.57f},
                {65, 2.24f},
                {72, 1.43f},
                {93, 2.01f},
                {109, 3.01f},
                {106, 2.97f},
                {77, 1.69f},
                {114, 2.09f},
                {98, 1.72f},
                {85, 2.46f},
                {113, 1.94f},
                {53, 1.77f},
                {106, 2.30f}
        };
    }
}

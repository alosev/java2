package lesson_2.BodyMassIndex;

public enum BMICategory {
    under,
    normal,
    over,
    obese;

    public static BMICategory FromBodyMassIndex(float index){
        BMICategory type = null;

        if(index < 18.5f){
            type = BMICategory.under;
        }
        else if (index < 25f){
            type = BMICategory.normal;
        }
        else if (index < 30f){
            type = BMICategory.over;
        }
        else{
            type = BMICategory.obese;
        }

        return type;
    }
}

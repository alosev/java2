package lesson_3.General.Task2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TelephoneDirectory{
    private HashMap<String, ArrayList<String>> items = new HashMap<>();

    public void add(String name, String phone){
        ArrayList<String> item = items.getOrDefault(name, new ArrayList<>());
        item.add(phone);
        items.put(name, item);
    }

    public ArrayList<String> get(String name){
        return items.getOrDefault(name, new ArrayList<>());
    }

    public HashMap<String, ArrayList<String>> getAll(){
        return items;
    }

    public void printInfo(){
        System.out.println("Телефонный справочник:");
        for (Map.Entry<String, ArrayList<String>> item: items.entrySet()){
            System.out.println("\t" + item.getKey() + ":" );

            for (String phone: item.getValue()){
                System.out.println("\t\t" + phone );
            }
        }
    }
}

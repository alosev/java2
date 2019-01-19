package lesson_6;

import lesson_6.Client.ChatClient;
import lesson_6.Server.Message;

import java.util.Scanner;

public class MainClient {
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        ChatClient chat =
                new ChatClient(
                    "localhost",
                    8196,
                    System.out::println, // обработчик логов
                    MainClient::printMessage // обработчик входящих сообщений
                );

        chat.connect("Саша");

        Scanner in = new Scanner(System.in);

        while (true){
            String text = in.nextLine();
            if(text.startsWith("/end")){
                break;
            }
            chat.sendMessage(text);
        }
    }

    private static void printMessage(Message msg){
        System.out.println(msg.name + ": " + msg.text);
    }
}

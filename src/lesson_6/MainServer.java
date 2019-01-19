package lesson_6;

import lesson_6.Server.ChatServer;
import lesson_6.Server.Message;

import java.util.Scanner;

public class MainServer {
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        ChatServer chatServer =
                new ChatServer(
                    8196,
                    1,
                    System.out::println, // обработчик логов
                    MainServer::printMessage // обработчик входящих сообщений
                );

        chatServer.start();

        Scanner in = new Scanner(System.in);

        while (true){
            String text = in.nextLine();
            if(text.startsWith("/end")){
                break;
            }
            chatServer.sendMessage(text);
        }

    }

    private static void printMessage(Message msg){
        System.out.println(msg.name + ": " + msg.text);
    }
}

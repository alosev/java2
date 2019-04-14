package lesson_7.Server;

import lesson_7.ChatLib.Message;
import lesson_7.ChatLib.Server;
import lesson_7.ChatLib.TextMessage;

import java.util.Scanner;

public class MainServer {
    public static void main(String[] args) {
        run();
    }

    private static void run() {
       Server chatServer =
                new Server(
                    8196,
                    10,
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
        if(msg instanceof TextMessage){
            TextMessage txtMsg = (TextMessage)msg;
            System.out.println(txtMsg.name + ": " + txtMsg.text);
        }

    }
}

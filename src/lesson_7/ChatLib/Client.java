package lesson_7.ChatLib;

import java.io.IOException;
import java.net.Socket;
import java.util.function.Consumer;

public class Client {
    private final String host;
    private final int port;
    private final Consumer<String> loggerHandler;
    private final Consumer<Message> inputHandler;

    private String login;
    private Socket socket = null;

    public Client(String host, int port, Consumer<String> loggerHandler, Consumer<Message> inputHandler) {
        this.host = host;
        this.port = port;
        this.loggerHandler = loggerHandler;
        this.inputHandler = inputHandler;
    }

    public void connect(String login){
        if(socket == null){

            this.login = login;

            Thread thread = new Thread(() -> {
                try{
                    // можно и выше инициализировать, но тут как бы уже есть try-catch ))
                    socket = new Socket(host, port);
                    loggerHandler.accept("Подключение установлено.");

                    while (true){
                        Message msg = Message.deserialization(socket.getInputStream());
                        inputHandler.accept(msg);
                    }
                }
                catch (Exception ex){
                    loggerHandler.accept("Ошибка: " + ex.getMessage());
                }
                finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    loggerHandler.accept("Подключение остановлено.");
                    socket = null;
                }
            });

            thread.setDaemon(true);
            thread.start();
        }
    }

    public void sendMessage(String text){
        if(socket != null){
            try {
                Message.serialization(new TextMessage(login, text, null), socket.getOutputStream());
            } catch (IOException e) {
                loggerHandler.accept(e.getMessage());
            }
        }
    }
}

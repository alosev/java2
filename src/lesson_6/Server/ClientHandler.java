package lesson_6.Server;

import java.io.*;
import java.net.Socket;
import java.util.function.Consumer;

class ClientHandler {
    private final Socket socket;
    private final Consumer<String> loggerHandler;
    private final Consumer<Message> inputHandler;

    ClientHandler(Socket socket, Consumer<String> loggerHandler, Consumer<Message> inputHandler) {
        this.socket = socket;
        this.loggerHandler = loggerHandler;
        this.inputHandler = inputHandler;

        init();
    }

    private void init(){
        Thread thread = new Thread(() -> {
            try{
                loggerHandler.accept("Клиент подключился.");

                while (true){
                    // десериализация выглядит понятнее, чем сериализация.
                    Message msg = Message.deserialization(socket.getInputStream());
                    inputHandler.accept(msg);
                }
            }
            catch (Exception ex){
                loggerHandler.accept("Ошибка клиента: " + ex.getMessage());
            }
            finally {
                loggerHandler.accept("Клиент отключился.");

                try {
                    socket.close();
                } catch (IOException e) {
                    loggerHandler.accept("Ошибка клиента: " + e.getMessage());
                }
            }
        });

        thread.setDaemon(true);
        thread.start();
    }

    void sendMessage(Message msg) {
        try {
            // какое-то сериализация исполнена в java непонятно.
            // Она не только сериализует, но и куда-то еще пишет. Было бы логичнее возвращать поток
            Message.serialization(msg, socket.getOutputStream());
        } catch (IOException e) {
            loggerHandler.accept(e.getMessage());
        }
    }
}

package lesson_6.Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.function.Consumer;

public class ChatServer {
    private final int port;
    private final Consumer<String> loggerHandler;
    private final Consumer<Message> inputHandler;
    private int countConnection;

    private Vector<ClientHandler> clients;
    private boolean status;

    public ChatServer(int port, int countConnection, Consumer<String> loggerHandler, Consumer<Message> inputHandler) {
        this.port = port;
        this.countConnection = countConnection;
        this.loggerHandler = loggerHandler;
        this.inputHandler = inputHandler;
        this.status = false;

        this.clients = new Vector<>();
    }

    public void start(){
        // даем стартануть только один раз
        if(!status){
            status = true;
            Thread thread = new Thread(() -> {
                try(ServerSocket server = new ServerSocket(port)) {
                    loggerHandler.accept("Сервер запущен.");
                    Socket socket;
                    ClientHandler client;

                    while (true){
                        socket = server.accept();
                        // держим только разрешенное кол-во подключений. Т.к. оно одно - не следим за отключениями
                        if(clients.size() < countConnection){
                            client = new ClientHandler(socket, loggerHandler, this::inputMessage);
                            clients.add(client);
                        }
                        else{
                            try{
                                socket.close();
                            }
                            catch (Exception ex){
                                loggerHandler.accept("Попытка лишнего подключения");
                            }
                        }
                    }
                }
                catch (Exception ex){
                    loggerHandler.accept("Ошибка сервера: " + ex.getMessage());
                }
                finally {
                    loggerHandler.accept("Сервер остановлен.");
                }
            });

            thread.setDaemon(true);
            thread.start();
        }
    }

    // сообщение от сервера
    public void sendMessage(String text) {
        Message msg = new Message("Server", text);
        sendMessage(msg);
    }

    // отправка сообщения клиентам
    private void sendMessage(Message msg){
        for (ClientHandler client: clients) {
            client.sendMessage(msg);
        }
    }

    // входящие сообщения от клиентов
    private void inputMessage(Message msg){
        inputHandler.accept(msg);
        sendMessage(msg);
    }
}

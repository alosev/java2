package lesson_7.ChatLib;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.function.Consumer;

public class Server {
    private final int port;
    private final Consumer<String> loggerHandler;
    private final Consumer<Message> inputHandler;
    private int countConnection;

    private Vector<ClientHandler> clients;
    private boolean status;

    public Server(int port, int countConnection, Consumer<String> loggerHandler, Consumer<Message> inputHandler) {
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
                            new ClientHandler(socket, loggerHandler, this::inputMessage);
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
        Message msg = new TextMessage("Server", text, null);
        sendMessage(msg);
    }

    // отправка сообщения клиентам
    private void sendMessage(Message msg){
        for (ClientHandler client: clients) {
            if(msg.to != null && msg.to == client.login){
                client.sendMessage(msg);
            }
        }
    }

    // входящие сообщения от клиентов
    private void inputMessage(Message msg){
        if(msg instanceof TextMessage){
            inputHandler.accept(msg);
            sendMessage(msg);
        }
        else if (msg instanceof ActionMessage){
            ActionMessage actionMessage = (ActionMessage)msg;
            if(actionMessage.actionType == ActionType.LOGIN){
                ClientHandler clientHandler = (ClientHandler) actionMessage.params[0];
                clients.add(clientHandler);
                loggerHandler.accept("Клиент подключился.");
            }
            else if(actionMessage.actionType == ActionType.LOGOUT){
                ClientHandler clientHandler = (ClientHandler) actionMessage.params[0];
                clients.remove(clientHandler);
                loggerHandler.accept("Клиент отключился.");
            }
        }
    }
}

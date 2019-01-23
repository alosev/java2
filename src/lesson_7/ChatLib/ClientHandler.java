package lesson_7.ChatLib;

import java.io.IOException;
import java.net.Socket;
import java.util.function.Consumer;

class ClientHandler {
    private final Socket socket;
    private final Consumer<String> loggerHandler;
    private final Consumer<Message> inputHandler;
    public String login;

    ClientHandler(Socket socket, Consumer<String> loggerHandler, Consumer<Message> inputHandler) {
        this.socket = socket;
        this.loggerHandler = loggerHandler;
        this.inputHandler = inputHandler;

        init();
    }

    public void init(){
        Thread thread = new Thread(() -> {
            try{

                while (true){
                    Message msg = Message.deserialization(socket.getInputStream());
                    if(msg instanceof ActionMessage){
                        ActionMessage actionMessage = (ActionMessage)msg;
                        if(actionMessage.actionType == ActionType.LOGIN){
                            login = (String)actionMessage.params[0];
                            String pass = (String)actionMessage.params[1];
                            String name = AuthService.getNickLoginAndPass(login, pass);

                            if(name != null){
                                Message result = new ActionMessage(ActionType.LOGIN, login, this);
                                sendMessage(result);
                                inputHandler.accept(result);
                                break;
                            }


                        }
                    }
                }

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
                try {
                    socket.close();
                } catch (IOException e) {
                    loggerHandler.accept("Ошибка клиента: " + e.getMessage());
                }

                Message msg = new ActionMessage(ActionType.LOGOUT, null,this);
                inputHandler.accept(msg);
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

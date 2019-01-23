package lesson_7.ChatLib;

import java.io.*;

// обмен сообщениями, а не строчками
public abstract class Message implements Serializable {
    public MessageType messageType;
    public String to;

    public Message(MessageType messageType, String to) {
        this.messageType = messageType;
        this.to = to;
    }

    public static void serialization(Message msg, OutputStream out) throws IOException {
        ObjectOutputStream msgOutput = new ObjectOutputStream(out);
        msgOutput.writeObject(msg);
    }

    public static Message deserialization(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream msgInput = new ObjectInputStream(in);
        return (Message) msgInput.readObject();
    }
}

enum MessageType{
    TEXT,
    ACTION
}



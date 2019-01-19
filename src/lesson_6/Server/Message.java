package lesson_6.Server;

import java.io.*;

// обмен сообщениями, а не строчками
public class Message implements Serializable {
    public String name;
    public String text;

    public Message(String name, String text) {
        this.name = name;
        this.text = text;
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

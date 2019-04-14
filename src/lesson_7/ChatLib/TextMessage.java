package lesson_7.ChatLib;

public class TextMessage extends Message{
    public String name;
    public String text;

    public TextMessage(String name, String text, String to) {
        super(MessageType.TEXT, to);
        this.name = name;
        this.text = text;
    }
}

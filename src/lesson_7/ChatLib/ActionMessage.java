package lesson_7.ChatLib;

public class ActionMessage extends Message {
    public ActionType actionType;
    public Object[] params;

    public ActionMessage(ActionType actionType, String to, Object... params) {
        super(MessageType.ACTION, to);
        this.actionType = actionType;
        this.params = params;
    }
}

enum ActionType{
    LOGIN,
    LOGOUT
}

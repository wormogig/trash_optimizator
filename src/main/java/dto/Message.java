package dto;

import java.io.Serializable;

public class Message implements Serializable {

    String message;

    public Message(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

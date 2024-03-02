package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {

    @JsonProperty
    private String senderName;

    @JsonProperty
    private String text;

    public Message(String senderName, String text) {
        this.senderName = senderName;
        this.text = text;
    }

    public Message() {
    }

    @Override
    public String toString() {
        return "Message{" +
                "senderName='" + senderName + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}

package com.rest.demo.repo;

import com.rest.demo.model.Message;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ChatRepo {
    private final List<Message> messages = new ArrayList<>();

    public List<Message> getAllMessages() {
        return messages;
    }

    public void sendMessage(Message message) {
        messages.add(message);
    }
}

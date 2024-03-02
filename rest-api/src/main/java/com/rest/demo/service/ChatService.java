package com.rest.demo.service;

import com.rest.demo.model.Message;
import com.rest.demo.repo.ChatRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepo chatRepo;
    private final Map<String, Map<String, Message>> viewedMessages = new HashMap<>();

    public List<Message> getNewMessages(String username) {
        viewedMessages.putIfAbsent(username, new HashMap<>());
        Map<String, Message> userViewedMessages = viewedMessages.get(username);

        return chatRepo.getAllMessages().stream()
                .filter(message -> !userViewedMessages.containsKey(message.getId()))
                .peek((message -> userViewedMessages.put(message.getId(), message)))
                .toList();
    }

    public void sendMessage(Message message) {
        message.setId(UUID.randomUUID().toString());
        chatRepo.sendMessage(message);
    }
}

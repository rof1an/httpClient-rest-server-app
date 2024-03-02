//package org.example;
//
//import lombok.Data;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Data
//public class SimpleChat implements IChat {
//    private List<Message> messages = new ArrayList<>();
//    private Map<String, Message> viewedMessages = new HashMap<>();
//
//    @Override
//    public List<Message> getNewMessages() {
//        return messages.stream()
//                .filter(message -> !viewedMessages.containsKey(message.getId()))
//                .peek(message -> viewedMessages.put(message.getId(), message))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public Message sendMessage(Message message) {
//        messages.add(message);
//        return message;
//    }
//}

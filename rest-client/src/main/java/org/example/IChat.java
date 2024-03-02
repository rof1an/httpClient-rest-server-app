package org.example;

import java.util.List;

public interface IChat {
    List<Message> getNewMessages(String username);
    Message sendMessage(Message message);
}

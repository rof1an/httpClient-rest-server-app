package com.rest.demo.controller;

import com.rest.demo.dto.MessageDto;
import com.rest.demo.mapper.ChatMapper;
import com.rest.demo.model.Message;
import com.rest.demo.service.ChatService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping(path = "api/v1/messages")
@RequiredArgsConstructor
public class ChatController {
    private final ChatMapper mapper;
    private final ChatService chatService;

    @GetMapping
    public List<MessageDto> getNewMessages(@RequestHeader("username") String username) {
        List<Message> newMessages = chatService.getNewMessages(username);
        return mapper.toDtoList(newMessages);
    }

    @PostMapping
    public void sendMessage(@RequestBody MessageDto messageDto) {
        Message messageModel = mapper.toModel(messageDto);
        chatService.sendMessage(messageModel);
    }
}

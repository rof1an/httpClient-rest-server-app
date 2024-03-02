package com.rest.demo.mapper;

import com.rest.demo.dto.MessageDto;
import com.rest.demo.model.Message;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChatMapper {
    @InheritInverseConfiguration
    MessageDto toDto(Message message);

    Message toModel(MessageDto messageDto);

    List<Message> toModelList(List<MessageDto> messageDtos);

    List<MessageDto> toDtoList(List<Message> messages);
}

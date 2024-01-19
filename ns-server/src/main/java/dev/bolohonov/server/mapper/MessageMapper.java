package dev.bolohonov.server.mapper;

import dev.bolohonov.server.dto.MessageDto;
import dev.bolohonov.server.model.Message;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class MessageMapper {

    public Message fromMessageDto(MessageDto messageDto) {
        return Message.builder()
                .msg(messageDto.getMsg())
                .msgType(messageDto.getMsgType())
                .status(messageDto.getStatus())
                .build();
    }

    public MessageDto toMessageDto(Message message) {
        return MessageDto.builder()
                .msg(message.getMsg())
                .msgType(message.getMsgType())
                .status(message.getStatus())
                .build();
    }

    public Collection<MessageDto> toMessageDto(Collection<Message> messages) {
        return messages.stream().map(m -> toMessageDto(m)).collect(Collectors.toList());
    }
}

package dev.bolohonov.server.service.impl;

import dev.bolohonov.server.dto.MessageDto;
import dev.bolohonov.server.model.MessageType;
import dev.bolohonov.server.service.MainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MainServiceImpl implements MainService {
    @Override
    public MessageDto sendSms(MessageDto messageDto) {
        return sendMsg(messageDto, MessageType.SMS);
    }

    @Override
    public MessageDto sendPush(MessageDto messageDto) {
        return sendMsg(messageDto, MessageType.PUSH);
    }

    @Override
    public MessageDto sendEmail(MessageDto messageDto) {
        return sendMsg(messageDto, MessageType.EMAIL);
    }

    private MessageDto sendMsg(MessageDto messageDto, MessageType messageType) {
        String status = messageType + " was sent";
        messageDto.setMsgType(messageType);
        messageDto.setStatus(status);
        log.info(status);
        return messageDto;
    }
}

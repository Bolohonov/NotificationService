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
        String status = MessageType.SMS + " was sent";
        messageDto.setMsgType(MessageType.SMS);
        messageDto.setStatus(status);
        log.info(status);
        return messageDto;
    }

    @Override
    public MessageDto sendPush(MessageDto messageDto) {
        String status = MessageType.PUSH + " was sent";
        messageDto.setMsgType(MessageType.PUSH);
        messageDto.setStatus(status);
        log.info(status);
        return messageDto;
    }

    @Override
    public MessageDto sendEmail(MessageDto messageDto) {
        String status = MessageType.EMAIL + " was sent";
        messageDto.setMsgType(MessageType.EMAIL);
        messageDto.setStatus(status);
        log.info(status);
        return messageDto;
    }
}

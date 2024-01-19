package dev.bolohonov.server.service.impl;

import dev.bolohonov.server.dto.MessageDto;
import dev.bolohonov.server.mapper.MessageMapper;
import dev.bolohonov.server.model.Message;
import dev.bolohonov.server.model.MessageType;
import dev.bolohonov.server.repo.MessageRepo;
import dev.bolohonov.server.service.MainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class MainServiceImpl implements MainService {

    private final MessageRepo messageRepo;
    private final MessageMapper messageMapper;

    @Autowired
    public MainServiceImpl(MessageRepo messageRepo, MessageMapper messageMapper) {
        this.messageRepo = messageRepo;
        this.messageMapper = messageMapper;
    }

    @Override
    public MessageDto sendSms(MessageDto messageDto) {
        MessageDto dto = sendMsg(messageDto, MessageType.SMS);
        messageRepo.save(messageMapper.fromMessageDto(dto));
        return dto;
    }

    @Override
    public MessageDto sendPush(MessageDto messageDto) {
        MessageDto dto = sendMsg(messageDto, MessageType.PUSH);
        messageRepo.save(messageMapper.fromMessageDto(dto));
        return dto;
    }

    @Override
    public MessageDto sendEmail(MessageDto messageDto) {
        MessageDto dto = sendMsg(messageDto, MessageType.EMAIL);
        messageRepo.save(messageMapper.fromMessageDto(dto));
        return dto;
    }

    @Override
    public Collection<MessageDto> getAllMessages() {
        List<Message> target = Collections.emptyList();
        messageRepo.findAll().forEach(target::add);
        return messageMapper.toMessageDto(target);
    }

    @Override
    public MessageDto getByMsg(String msg) {
        return messageMapper.toMessageDto(messageRepo.findByMsg(msg));
    }

    private MessageDto sendMsg(MessageDto messageDto, MessageType messageType) {
        String status = messageType + " was sent";
        messageDto.setMsgType(messageType);
        messageDto.setStatus(status);
        log.info(status);
        return messageDto;
    }
}

package dev.bolohonov.server.service;

import dev.bolohonov.server.dto.MessageDto;

import java.util.Collection;

public interface MainService {

    MessageDto sendSms(MessageDto messageDto);

    MessageDto sendPush(MessageDto messageDto);

    MessageDto sendEmail(MessageDto messageDto);

    Collection<MessageDto> getAllMessages();

    MessageDto getByMsg(String msg);
}

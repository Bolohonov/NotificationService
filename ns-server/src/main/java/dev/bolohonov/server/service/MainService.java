package dev.bolohonov.server.service;

import dev.bolohonov.server.dto.MessageDto;

public interface MainService {

    MessageDto sendSms(MessageDto messageDto);

    MessageDto sendPush(MessageDto messageDto);

    MessageDto sendEmail(MessageDto messageDto);
}

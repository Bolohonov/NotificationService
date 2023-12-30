package dev.bolohonov.server.controller;

import dev.bolohonov.server.dto.MessageDto;
import dev.bolohonov.server.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class MainController {

    private final MainService mainService;

    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @PostMapping("/sms")
    public ResponseEntity<MessageDto> postSms(@Validated @RequestBody MessageDto messageDto) {
        return ResponseEntity.ok(mainService.sendSms(messageDto));
    }

    @PostMapping("/push")
    public ResponseEntity<MessageDto> postPush(@Validated @RequestBody MessageDto messageDto) {
        return ResponseEntity.ok(mainService.sendPush(messageDto));
    }

    @PostMapping("/email")
    public ResponseEntity<MessageDto> postEmail(@Validated @RequestBody MessageDto messageDto) {
        return ResponseEntity.ok(mainService.sendEmail(messageDto));
    }
}

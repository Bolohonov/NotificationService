package dev.bolohonov.server.controller;

import dev.bolohonov.server.dto.MessageDto;
import dev.bolohonov.server.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/notifications")
public class MainController {

    private final MainService mainService;

    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @PostMapping("sms")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<MessageDto> postSms(@Validated @RequestBody MessageDto messageDto) {
        return ResponseEntity.ok(mainService.sendSms(messageDto));
    }

    @PostMapping("push")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<MessageDto> postPush(@Validated @RequestBody MessageDto messageDto) {
        return ResponseEntity.ok(mainService.sendPush(messageDto));
    }

    @PostMapping("email")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<MessageDto> postEmail(@Validated @RequestBody MessageDto messageDto) {
        return ResponseEntity.ok(mainService.sendEmail(messageDto));
    }

    @GetMapping("all")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Collection<MessageDto>> test() {
        return ResponseEntity.ok(mainService.getAllMessages());
    }

    @GetMapping("byMsg")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<MessageDto> test(@RequestParam String msg) {
        return ResponseEntity.ok(mainService.getByMsg(msg));
    }
}

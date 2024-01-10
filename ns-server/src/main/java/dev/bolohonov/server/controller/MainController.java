package dev.bolohonov.server.controller;

import dev.bolohonov.server.dto.MessageDto;
import dev.bolohonov.server.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<MessageDto> postSms(@Validated @RequestBody MessageDto messageDto) {
        return ResponseEntity.ok(mainService.sendSms(messageDto));
    }

    @PostMapping("/push")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<MessageDto> postPush(@Validated @RequestBody MessageDto messageDto) {
        return ResponseEntity.ok(mainService.sendPush(messageDto));
    }

    @PostMapping("/email")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<MessageDto> postEmail(@Validated @RequestBody MessageDto messageDto) {
        return ResponseEntity.ok(mainService.sendEmail(messageDto));
    }

    @GetMapping("/test")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public String greeting() {
        return "Hello all! It's a public endpoint. Every user can reach me.";
    }

    @PostMapping("/test2")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public String test(@RequestParam String msg) {
        return msg;
    }
}

package dev.bolohonov.server.dto;

import dev.bolohonov.server.model.MessageType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto {

    @NotNull(message = "Необходимо указать текст сообщения")
    private String msg;

    private MessageType msgType;

    private String status;
}

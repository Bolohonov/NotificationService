package dev.bolohonov.server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash("Message")
public class Message implements Serializable {

    @Id
    private String id;

    private String msg;

    private MessageType msgType;

    private String status;

}

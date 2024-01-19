package dev.bolohonov.server.repo;

import dev.bolohonov.server.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepo extends CrudRepository<Message, String> {

    Message findByMsg(String msg);
}

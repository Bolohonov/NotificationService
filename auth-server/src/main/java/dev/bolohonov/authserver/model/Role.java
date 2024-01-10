package dev.bolohonov.authserver.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "user_roles")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    /**
     * уникальный идентификатор пользователя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * роль пользователя
     */
    String name;

    /**
     * Роли пользователя
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

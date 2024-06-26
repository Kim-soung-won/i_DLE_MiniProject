package com.i.minishopping.Domains.User;

import com.i.minishopping.Domains.ENUM.DOIT;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name="user_log")
@NoArgsConstructor
public class UserLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userlog_id")
    private Long id;

    @Column(name = "created_who")
    private Long user_id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Column(name = "doit")
    @Enumerated(EnumType.STRING)
    private DOIT doit;

    @Column(name = "product_id")
    private Long product_id;

    @Builder
    private UserLog(Long id,String name, LocalDateTime created_at , DOIT doit){
        this.user_id = id;
        this.name = name;
        this.created_at = created_at;
        this.doit = doit;
    }
}

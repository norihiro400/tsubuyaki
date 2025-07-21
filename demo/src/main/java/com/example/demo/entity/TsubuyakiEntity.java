package com.example.demo.entity;

import java.sql.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@Table(name = "tsubuyaki")
@AllArgsConstructor
public class TsubuyakiEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
  
    private String comment; //コメント

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt; //作成日時 
}

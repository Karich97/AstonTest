package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long targetId;
    private Double amount;
    private Status status;

    public Transaction(Long targetId, Double amount) {
        this.targetId = targetId;
        this.amount = amount;
        this.status = Status.IN_PROGRESS;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long targetId;
    private Double amount;
    @Enumerated(EnumType.STRING)
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

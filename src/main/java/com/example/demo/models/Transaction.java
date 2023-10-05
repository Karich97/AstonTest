package com.example.demo.models;

public class Transaction {
    private int id;
    private int ownerId;
    private int targetId;
    private int amount;

    public Transaction(int id, int ownerId, int targetId, int amount) {
        this.id = id;
        this.ownerId = ownerId;
        this.targetId = targetId;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

package com.example.GroupPay.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @UuidGenerator
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "account_no", unique = true, nullable = false)
    private long accountNo;

    @Column(name = "card_number", nullable = false)
    private int cardNum;

    @Column(name = "balance", nullable = false)
    private double balance;

    public Account() {
    }

    public Account(UUID id, int userId, long accountNo, int cardNum, int balance) {
        this.id = id;
        this.userId = userId;
        this.accountNo = accountNo;
        this.cardNum = cardNum;
        this.balance = balance;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(long accountNo) {
        this.accountNo = accountNo;
    }

    public int getCardNum() {
        return cardNum;
    }

    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

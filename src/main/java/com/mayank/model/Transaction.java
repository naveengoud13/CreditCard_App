package com.mayank.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {
	private Integer transactionId;
	private LocalDateTime timestamp;
	private TransactionType transactionType;
	private Integer amount;
	private User user;
	
	public Transaction() {
		
	}

	public Transaction(Integer transactionId, LocalDateTime timestamp, TransactionType transactionType, Integer amount,
			User user) {
		super();
		this.transactionId = transactionId;
		this.timestamp = timestamp;
		this.transactionType = transactionType;
		this.amount = amount;
		this.user = user;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, timestamp, transactionId, transactionType, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(timestamp, other.timestamp)
				&& Objects.equals(transactionId, other.transactionId) && transactionType == other.transactionType
				&& Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", timestamp=" + timestamp + ", transactionType="
				+ transactionType + ", amount=" + amount + ", user=" + user + "]";
	}

}

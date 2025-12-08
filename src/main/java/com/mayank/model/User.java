package com.mayank.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
	private Integer userId;
	private String name;
	private String email;
	private CreditCard creditCard;
	private List<Transaction> transactions = new ArrayList<>();

	public User(Integer userId, String name, String email, CreditCard creditCard, List<Transaction> transactions) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.creditCard = creditCard;
		this.transactions = transactions;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public int hashCode() {
		return Objects.hash(creditCard, email, name, transactions, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(creditCard, other.creditCard) && Objects.equals(email, other.email)
				&& Objects.equals(name, other.name) && Objects.equals(userId, other.userId);
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", creditCard=" + creditCard + "]";
	}

}

package com.mayank.model;

import java.util.Objects;

public class CreditCard {

	private String credit_card_number;
	private Integer cvv;
	private String expiryDate;
	private static final Integer LIMIT_AMOUNT = 30000;
	private Integer remainingLimit = LIMIT_AMOUNT;

	public CreditCard(String credit_card_number, Integer cvv, String expiryDate) {
		super();
		this.credit_card_number = credit_card_number;
		this.cvv = cvv;
		this.expiryDate = expiryDate;
	}

	public String getCredit_card_number() {
		return credit_card_number;
	}

	public Integer getCvv() {
		return cvv;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public static Integer getLimitAmount() {
		return LIMIT_AMOUNT;
	}

	public Integer getRemainingLimit() {
		return remainingLimit;
	}

	public void setRemainingLimit(Integer remainingLimit) {
		this.remainingLimit = remainingLimit;
	}

	@Override
	public int hashCode() {
		return Objects.hash(credit_card_number, cvv, expiryDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreditCard other = (CreditCard) obj;
		return Objects.equals(credit_card_number, other.credit_card_number) && Objects.equals(cvv, other.cvv)
				&& Objects.equals(expiryDate, other.expiryDate);
	}

	@Override
	public String toString() {
		return "CreditCard [credit_card_number=" + credit_card_number + ", cvv=" + cvv + ", expiryDate=" + expiryDate
				+ ", Limit Amount  =" + LIMIT_AMOUNT + ", remainingLimit=" + remainingLimit + "]";
	}

}

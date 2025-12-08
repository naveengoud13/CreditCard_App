package com.mayank.service;

import java.time.LocalDateTime;
import java.util.List;

import com.mayank.exception.CreditCardException;
import com.mayank.exception.LoginException;
import com.mayank.exception.TimeException;
import com.mayank.exception.TransactionException;
import com.mayank.model.Transaction;
import com.mayank.model.TransactionType;

public interface UserService {

	String login(String email) throws LoginException;

	Transaction makeTransaction(Integer amount,TransactionType transactionType) throws LoginException, TransactionException, CreditCardException;

	List<Transaction> viewTransactions() throws LoginException, TransactionException;

	//Transaction makeRepayment(Integer amount) throws LoginException, TransactionException;

	LocalDateTime timeTravel(Integer days) throws LoginException, TransactionException, TimeException;

}

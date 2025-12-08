package com.mayank.service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import com.mayank.email.GmailSender;
import com.mayank.exception.CreditCardException;
import com.mayank.exception.LoginException;
import com.mayank.exception.TimeException;
import com.mayank.exception.TransactionException;
import com.mayank.model.CreditCard;
import com.mayank.model.Transaction;
import com.mayank.model.TransactionType;
import com.mayank.model.User;

public class UserServicesImpl implements UserService {

	private Set<User> users = new HashSet<>();
	private List<Transaction> transactions = new ArrayList<>();
	private Set<CreditCard> creditCards = new HashSet<>();

	private static LocalDateTime presentDateTime = LocalDateTime.now().minusMonths(1);

	private static int transactionId;

	{

		CreditCard cc1 = new CreditCard("1234567812345678", 567, "04/28");
		CreditCard cc2 = new CreditCard("1122334455667788", 123, "01/29");
		CreditCard cc3 = new CreditCard("1111222233334444", 456, "06/27");

		creditCards.add(cc1);
		creditCards.add(cc2);
		creditCards.add(cc3);

		User user1 = new User(1, "madhu", "kinneramadhu123@gmail.com", cc1, new ArrayList<Transaction>());
		User user2 = new User(2, "karun", "kkarun701@gmail.com", cc2, new ArrayList<Transaction>());
		User user3 = new User(3, "ganesh", "ganeshsagar373@gmail.com", cc3, new ArrayList<Transaction>());

		users.add(user1);
		users.add(user2);
		users.add(user3);

	}

	private User user;

	@Override
	public String login(String email) throws LoginException {
		List<User> filteredUsers = users.stream().filter(u -> u.getEmail().equals(email)).collect(Collectors.toList());
		if (filteredUsers.isEmpty())
			throw new LoginException("No User Found With Email " + email);
		else {

			int generatedOTP = GmailSender.generateRandomNumber();

			String subject = "OTP - Only share with your project mates ";
			String text = "Your OTP for Login is " + generatedOTP;
			if (GmailSender.sendGmail(email, subject, text))
				System.out.println("Enter OTP sent to gmail " + email);
			else
				System.out.println("Email Not Found " + email);

			Scanner sc = new Scanner(System.in);
			int inputOTP = sc.nextInt();

			if (inputOTP != generatedOTP)
				throw new LoginException("OTP Not Matched");

			User filteredUser = filteredUsers.get(0);
			user = filteredUser;

		}
		return "Login Successful with email " + email;
	}

	@SuppressWarnings("static-access")
	@Override
	public Transaction makeTransaction(Integer amount, TransactionType transactionType)
			throws LoginException, CreditCardException {

		Transaction transaction = new Transaction();

		if (user == null)
			throw new LoginException("Login to Make Transaction");

		int currentAmount = user.getCreditCard().getRemainingLimit();

		boolean condition1 = transactionType.equals(TransactionType.REPAY)
				&& (user.getCreditCard().getLimitAmount() - currentAmount) >= amount;

		boolean condition2 = transactionType.equals(TransactionType.SPENT) && (currentAmount >= amount);

		if (condition1 || condition2) {
			amount = transactionType.equals(TransactionType.SPENT) ? amount : (-1 * amount);

			user.getCreditCard().setRemainingLimit(currentAmount - amount);

			transaction.setAmount(amount);
			transaction.setTimestamp(presentDateTime);
			transaction.setTransactionId(++transactionId);
			transaction.setTransactionType(transactionType);
			transaction.setUser(user);

			transactions.add(transaction);

		} else {
			throw new CreditCardException("Not Sufficient Balance in Card\n Available Bal : "
					+ user.getCreditCard().getRemainingLimit() + "\n Requested Amount : " + amount);
		}

		return transaction;

	}

	@Override
	public List<Transaction> viewTransactions() throws LoginException, TransactionException {
		if (user == null)
			throw new LoginException("Login to Make Transaction");

		List<Transaction> trans = new ArrayList<>();

		trans = transactions.stream().filter(t -> t.getUser().equals(user)).collect(Collectors.toList());

		if (trans.isEmpty())
			throw new TransactionException("No Transactions At the Moment..!");

		return trans;
	}

	@Override
	public LocalDateTime timeTravel(Integer days) throws LoginException, TimeException, TransactionException {
		if (user == null)
			throw new LoginException("Login to Make Transaction");

		

		if (presentDateTime.plusDays(days).isAfter(LocalDateTime.now()))
			throw new TimeException("You Can't go to future ....");
		
		presentDateTime = presentDateTime.plusDays(days);

		LocalDate billDate = LocalDate.of(Year.now().getValue(), LocalDate.now().getMonth(), 21);

		if (presentDateTime.isAfter(LocalDateTime.of(billDate, LocalTime.now()))) {

			String subject = "Bill History of CreditCard";
			String text = sentBillToString();

			GmailSender.sendGmail(user.getEmail(), subject, text);
			System.out.println("History Sent to Bank");
		}

		return presentDateTime;
	}

	private String sentBillToString() throws TransactionException {

		StringBuilder sb = new StringBuilder();

		if (transactions.isEmpty())
			throw new TransactionException("No Transactions Found ");

		for (Transaction t : transactions) {
			sb.append(t.getTimestamp() + "\t");
			sb.append(t.getTransactionType() + "\t");
			sb.append(t.getAmount() + "\t");
			sb.append("\n");
		}

		return sb.toString();

	}

}

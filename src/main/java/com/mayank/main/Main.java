package com.mayank.main;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import com.mayank.exception.CreditCardException;
import com.mayank.exception.LoginException;
import com.mayank.exception.TimeException;
import com.mayank.exception.TransactionException;
import com.mayank.model.Transaction;
import com.mayank.model.TransactionType;
import com.mayank.service.UserService;
import com.mayank.service.UserServicesImpl;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		UserService services = new UserServicesImpl();

		System.out.println("Choose Numbers to Navigate ");
		System.out.println("Enter 9 to Exit ");

		while (true) {
			System.out.println("1. Login");
			System.out.println("2. Make Transaction ");
			System.out.println("3. View Transaction ");
			System.out.println("4. Time Travel");

			int choice = sc.nextInt();

			if (choice == 1) {
				System.out.println("Login");
				System.out.println("====================");
				System.out.println("Enter Email Address ");
				String email = sc.next();
				try {
					String message = services.login(email);
					System.out.println(message);
				} catch (LoginException e) {
					System.out.println(e.getMessage());
				}

			} else if (choice == 2) {
				System.out.println("Make Transaction ");
				System.out.println("====================");
				System.out.println("Enter Amount ");
				int amount = sc.nextInt();

				TransactionType transactionType;
				while (true) {
					System.out.println("Choose Transaction Type ");
					System.out.println("-------------------------");
					System.out.println("1. SPENT");
					System.out.println("2. REPAY");
					int innerChoice = sc.nextInt();
					if (innerChoice == 1) {
						transactionType = TransactionType.SPENT;
						break;
					} else if (innerChoice == 2) {
						transactionType = TransactionType.REPAY;
						break;
					} else
						System.out.println("Invalid Choice");
				}
				try {
					Transaction transaction = services.makeTransaction(amount, transactionType);
					System.out.println(transaction);
				} catch (LoginException | TransactionException | CreditCardException e) {
					System.out.println(e.getMessage());
				}
			} else if (choice == 3) {
				System.out.println("View Transactions");
				System.out.println("==================");
				try {
					List<Transaction> transactions = services.viewTransactions();

					transactions.forEach(System.out::println);

				} catch (LoginException | TransactionException e) {
					System.out.println(e.getMessage());
				}
			} else if (choice == 4) {
				System.out.println("Time Travel");
				System.out.println("============");

				System.out.println("Enter Number Of Days");
				int days = sc.nextInt();

				try {
					LocalDateTime presentDateTime = services.timeTravel(days);
					System.out.println("You are at " + presentDateTime);
				} catch (LoginException | TimeException | TransactionException e) {
					System.out.println(e.getMessage());
				}

			} else if (choice == 9) {
				System.out.println("Thank you Have a Great Day ");
				break;
			} else {
				System.out.println("Invalid Choice");
			}

		}

	}

}

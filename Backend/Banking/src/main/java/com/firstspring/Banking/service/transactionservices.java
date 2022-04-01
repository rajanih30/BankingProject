package com.firstspring.Banking.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.firstspring.Banking.entity.customer;
import com.firstspring.Banking.entity.transaction;
import com.firstspring.Banking.respository.customerrepository;
import com.firstspring.Banking.respository.transactionrepository;

@Service
public class transactionservices {
	@Autowired
	private transactionrepository tr;
	@Autowired
	private customerrepository cr;

	public boolean deposite(transaction t) {
		String accountNum = t.getAccountno();
		customer c = cr.findByaccountno(accountNum);
		if (c != null) {
			double balance = c.getBalance();
			balance = balance + t.getAmount();
			c.setBalance(balance);
			cr.save(c);
			String date = LocalDate.now().toString();
			t.setDate(date);
			tr.save(t);
			return true;
		} else {
			return false;
		}
	}

	public String withdraw(transaction t) {
		String accountNum = t.getAccountno();
		customer c = cr.findByaccountno(accountNum);
		if (c != null) {
			double balance = c.getBalance();
			balance = balance - t.getAmount();
			c.setBalance(balance);
			cr.save(c);
			String date = LocalDate.now().toString();
			t.setDate(date);
			tr.save(t);
			return "Transaction completed successfully";
		}
		return "Transaction Failed...";
	}

	public List<transaction> getAll(String accountNum) {
		return tr.findAllByaccountno(accountNum);
	}

	public String fundtransfer(List<transaction> trans) {
		for (transaction t : trans) {
			if (t.getType().equals("CREDIT")) {
				deposite(t);
			}
			if (t.getType().equals("DEBIT")) {
				withdraw(t);
			}
		}
		return "Success";
	}
}

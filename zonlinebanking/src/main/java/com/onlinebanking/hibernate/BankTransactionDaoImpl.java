package com.onlinebanking.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlinebanking.domain.BankTransaction;

@Repository
public class BankTransactionDaoImpl implements BankTransactionDao {

	@Autowired
	SessionFactory sessionFactory;

	Session session;

	@Override
	public BankTransaction save(BankTransaction bankTransaction) {
		BankTransaction bankTransactionFromDB = findById(bankTransaction.getBankTransactionId());
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			if (bankTransactionFromDB != null) {
				session.saveOrUpdate(bankTransaction);
			} else {
				session.save(bankTransaction);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Unable to save transaction..." + e.getMessage());

		} finally {
			session.close();
		}
		return bankTransaction;
	}

	@Override
	public BankTransaction findById(long id) {

		BankTransaction bankTransaction = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			bankTransaction = session.get(BankTransaction.class, id);
			session.getTransaction().commit();
			System.out.println("Bank transaction located: " + bankTransaction);
		} catch (Exception e) {
			System.out.println("Unable to retrieve bank transaction....");
		}
		return bankTransaction;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BankTransaction> findAll() {

		try {
			session = sessionFactory.openSession();

			var result = session.getSession().createQuery("from BankTransaction").list();

			return result;

		} catch (Exception e) {
			return new ArrayList<>();
		} finally {
			session.close();
		}
	}

}

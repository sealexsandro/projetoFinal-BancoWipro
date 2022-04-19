//package com.wipro.projetofinal.service;
//
//import java.util.List;
//
//import com.wipro.projetofinal.entities.Account;
//
//public class ValidationCreditCard {
//
//	public static boolean existNumberCreditCard(String number, List<Account> accounts) {
//
//		for (Account account : accounts) {
//			if (account.getCreditCard() != null) {
//				if (account.getCreditCard().getCardNumber().equals(number)) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}
//
//}

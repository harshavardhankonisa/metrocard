package com.example.geektrust.passenger;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Wallet {
	private static Map<String, BigDecimal> metroCards = new HashMap<>(); // wallets of passengers are stored here for easy acess

	public static void addCard(String cardId, BigDecimal cardBalance) {
		metroCards.put(cardId, cardBalance);
	}

	public static BigDecimal getCardBalance(String cardId) {
		return metroCards.get(cardId);
	}

	public static void addCardBalance(String cardId, BigDecimal amountToBeAdded) {
		metroCards.put(cardId, metroCards.get(cardId).add(amountToBeAdded));
	}

	public static boolean containsCard(String cardId) {
		return metroCards.containsKey(cardId);
	}
}

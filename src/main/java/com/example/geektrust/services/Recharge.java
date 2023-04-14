package com.example.geektrust.services;

import java.math.BigDecimal;

import com.example.geektrust.passenger.Wallet;
import com.example.geektrust.station.Revenue;

public class Recharge {
	// Reacharges the wallet and pays the tax to metro and updates the wallet
	public static void rechargeCard(String cardId, String station, BigDecimal rechargeAmount) {
		BigDecimal tax = rechargeAmount.multiply(new BigDecimal(2)).divide(new BigDecimal(100));
		Wallet.addCard(cardId, new BigDecimal(0)); // add the balance to zero because recharge is for single journey
		if (Revenue.containsStation(station)) {
			Revenue.addStationRevenue(station, tax);
		} else {
			Revenue.addStation(station, tax);
		}
	}
}

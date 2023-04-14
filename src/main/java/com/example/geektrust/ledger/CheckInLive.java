package com.example.geektrust.ledger;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.example.geektrust.passenger.Wallet;
import com.example.geektrust.station.Discount;
import com.example.geektrust.station.PassengerCount;
import com.example.geektrust.station.Revenue;
import com.example.geektrust.services.Recharge;

public class CheckInLive {

	private static Map<String, String[]> checkInLedger = new HashMap<>(); // <cardId+age , [Station, '1'or'0']> gives
																			// unique identity to individual passenger

	public static void live(String[] checkInStat) {
		String cardId = checkInStat[0];
		String age = checkInStat[1];
		String station = checkInStat[2];

		BigDecimal walletBalance = Wallet.getCardBalance(cardId);
		BigDecimal charge = PassengerCharges.getCharge(age);

		String checkInLedgerKey = cardId + " " + age;

		// passenger count values are updated here
		if (PassengerCount.containsStation(station) && PassengerCount.containsAge(station, age)) {
			PassengerCount.addCount(station, age);
		} else if (PassengerCount.containsStation(station) && !PassengerCount.containsAge(station, age)) {
			Map<String, Integer> temp = PassengerCount.getPassengerCount(station);
			temp.put(age, 1);
		} else {
			Map<String, Integer> temp = new HashMap<>();
			temp.put(age, 1);
			PassengerCount.addStation(station, temp);
		}

		// discount is given here
		if (checkInLedger.containsKey(checkInLedgerKey) && !checkInLedger.get(checkInLedgerKey)[0].equals(station)
				&& checkInLedger.get(checkInLedgerKey)[1].equals("1")) {
			String[] checkInDetails = new String[2];
			checkInDetails[0] = station;
			checkInDetails[1] = "0";
			checkInLedger.put(checkInLedgerKey, checkInDetails);

			BigDecimal discountPercentage = new BigDecimal(2);
			BigDecimal discount = charge.divide(discountPercentage);
			if (Discount.containsStation(station)) {
				Discount.addDiscountGiven(station, discount);
			} else {
				Discount.addStation(station, discount);
			}

			charge = charge.divide(new BigDecimal(2));
		} else {
			String[] checkInDetails = new String[2];
			checkInDetails[0] = station;
			checkInDetails[1] = "1";
			checkInLedger.put(checkInLedgerKey, checkInDetails);
		}

		// Recharges the wallet
		if (walletBalance.compareTo(charge) < 0) {
			Recharge.rechargeCard(cardId, station, charge.subtract(walletBalance));
		} else {
			BigDecimal newWalletBalance = walletBalance.subtract(charge);
			Wallet.addCard(cardId, newWalletBalance); // updates card balance after recharges
		}

		// adds station to list of stations for revenue wallet of each station
		if (Revenue.containsStation(station)) {
			Revenue.addStationRevenue(station, charge);
		} else {
			Revenue.addStation(station, charge);
		}
	}
}

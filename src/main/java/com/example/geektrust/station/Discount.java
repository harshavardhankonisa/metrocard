package com.example.geektrust.station;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Discount {
	private static Map<String, BigDecimal> totalDiscountGiven = new HashMap<>(); // discount given are stored here

	public static void addStation(String stationName, BigDecimal discountGiven) {
		totalDiscountGiven.put(stationName, discountGiven);
	}

	public static BigDecimal getStationDiscountGivenTillDate(String stationName) {
		if (totalDiscountGiven.get(stationName) == null)
			return new BigDecimal(0);
		return totalDiscountGiven.get(stationName);
	}

	public static void addDiscountGiven(String stationName, BigDecimal discountGiven) {
		totalDiscountGiven.put(stationName, totalDiscountGiven.get(stationName).add(discountGiven));
	}

	public static boolean containsStation(String stationName) {
		return totalDiscountGiven.containsKey(stationName);
	}
}

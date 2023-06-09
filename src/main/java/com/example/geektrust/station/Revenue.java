package com.example.geektrust.station;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Revenue {
	private static Map<String, BigDecimal> stationRevenue = new HashMap<>();
	// revenue generated by each station is stored here

	public static void addStation(String stationName, BigDecimal revenue) {
		stationRevenue.put(stationName, revenue);
	}

	public static BigDecimal getStaionRevenue(String stationName) {
		if (stationRevenue.get(stationName) == null)
			return new BigDecimal(0);
		return stationRevenue.get(stationName);
	}

	public static void addStationRevenue(String stationName, BigDecimal charges) {
		stationRevenue.put(stationName, stationRevenue.get(stationName).add(charges));
	}

	public static boolean containsStation(String stationName) {
		return stationRevenue.containsKey(stationName);
	}
}

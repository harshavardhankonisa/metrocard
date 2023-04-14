package com.example.geektrust.station;

import java.util.HashMap;
import java.util.Map;

public class PassengerCount {
	private static Map<String, Map<String, Integer>> stationCount = new HashMap<>(); // passenger count is stored here

	public static boolean containsStation(String station) {
		return stationCount.containsKey(station);
	}

	public static boolean containsAge(String station, String age) {
		return stationCount.get(station).containsKey(age);
	}

	public static void addCount(String station, String age) {
		Map<String, Integer> pc = stationCount.get(station);
		int num = pc.get(age);
		pc.put(age, ++num);
		stationCount.put(station, pc);
	}

	public static Map<String, Integer> getPassengerCount(String station) {
		return stationCount.get(station);
	}

	public static void addStation(String station, Map<String, Integer> passengerCount) {
		stationCount.put(station, passengerCount);
	}

	public static int getPassengerCount(String station, String age) {
		return stationCount.get(station).get(age);
	}
}

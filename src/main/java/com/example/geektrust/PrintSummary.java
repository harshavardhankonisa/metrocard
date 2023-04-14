package com.example.geektrust;

import java.util.Map;
import java.util.PriorityQueue;

import com.example.geektrust.station.Discount;
import com.example.geektrust.station.PassengerCount;
import com.example.geektrust.station.Revenue;

public class PrintSummary {
    public static void print() {
        String[] stations = { "CENTRAL", "AIRPORT" }; // stations data to be printed

        for (String station : stations) {
            System.out.println("TOTAL_COLLECTION " + station + " " + Revenue.getStaionRevenue(station) + " "
                    + Discount.getStationDiscountGivenTillDate(station));
            System.out.println("PASSENGER_TYPE_SUMMARY");
            Map<String, Integer> temp = PassengerCount.getPassengerCount(station);
            if (temp == null)
                continue;
            PriorityQueue<String> sortedCount = convertToQueue(temp); // sorts hashmap of age:values through queues
            while (!sortedCount.isEmpty()) {
                String passenger = sortedCount.poll();
                System.out.println(passenger + " " + PassengerCount.getPassengerCount(station, passenger));
            }
        }
    }

    private static PriorityQueue<String> convertToQueue(Map<String, Integer> map) {
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            priorityQueue.add(entry.getKey());
        }
        return priorityQueue;
    }
}

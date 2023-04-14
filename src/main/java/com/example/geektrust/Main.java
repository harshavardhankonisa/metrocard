package com.example.geektrust;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

import com.example.geektrust.ledger.CheckInLive;
import com.example.geektrust.passenger.Wallet;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            FileInputStream fis = new FileInputStream(args[0]); // input file is read here
            Scanner sc = new Scanner(fis); // input scanner for file

            while (sc.hasNextLine()) {

                String[] input = sc.nextLine().split(" ", 2);
                switch (input[0]) {

                    case "BALANCE":
                        String[] wallet = input[1].split(" ", 2); // wallet details are fetched from here
                        String cardId = wallet[0];
                        BigDecimal walletBalance = new BigDecimal(wallet[1]);
                        if (Wallet.containsCard(cardId)) {
                            System.err.println(cardId + " IS ALREADY EXISTS, PLEASE CHECK THE INPUT");
                        } else {
                            Wallet.addCard(cardId, walletBalance); // Add's Metrocard's to Wallets
                        }
                        break;

                    case "CHECK_IN":
                        String[] checkInStat = input[1].split(" ", 3);// checkIn is fetched from here
                        String cardIdUsed = checkInStat[0];
                        if (Wallet.containsCard(cardIdUsed)) {
                            CheckInLive.live(checkInStat); // Main Function of the code fetches values from here
                        } else {
                            System.err.println(cardIdUsed + " METROCARD NUMBER IS INVALID");
                        }
                        break;

                    case "PRINT_SUMMARY":
                        PrintSummary.print(); // prints the output
                        break;

                    default:
                        System.out
                                .println("Unknown Command Given. Expected to be BALANCE or CHECK_IN or PRINT_SUMMARY");
                        break;
                }
            }

            sc.close(); // closes the scanner
        } catch (IOException e) {
            throw new IOException("Error while reading the INPUT in Main function");
        }
    }
}

package com.example.geektrust.ledger;

import java.math.BigDecimal;

public class PassengerCharges {
	// passenger travel charges fixed by metro authority are here
	private static final BigDecimal ADULT = new BigDecimal(200);
	private static final BigDecimal SENIOR_CITIZEN = new BigDecimal(100);
	private static final BigDecimal KID = new BigDecimal(50);

	public static BigDecimal getCharge(String age) {
		BigDecimal charge = new BigDecimal(0);
		switch (age) {
			case "ADULT":
				charge = ADULT;
				break;
			case "SENIOR_CITIZEN":
				charge = SENIOR_CITIZEN;
				break;
			case "KID":
				charge = KID;
				break;
		}
		return charge;
	}
}

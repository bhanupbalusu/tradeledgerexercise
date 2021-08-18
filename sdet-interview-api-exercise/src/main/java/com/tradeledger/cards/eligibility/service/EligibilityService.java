package com.tradeledger.cards.eligibility.service;

import com.tradeledger.cards.eligibility.domain.Applicant;
import com.tradeledger.cards.eligibility.domain.Eligibility;
import org.springframework.stereotype.Service;

import com.tradeledger.cards.eligibility.domain.Eligibility.EligibilityBuilder;

import static com.tradeledger.cards.eligibility.domain.Eligibility.newEligibility;

import java.util.stream.Stream;

@Service
public class EligibilityService {
	
	private static final String CARD_1 = "C1";
	private static final String CARD_2 = "C2";
	
	public Eligibility checkEligibilityFor(final Applicant applicant) {
		
		switch (applicant.getName()) {
		case "Boris":
			return eligibilityOf(CARD_1);

		case "Angela":
			return eligibilityOf(CARD_1, CARD_2);
			
		case "Theresa":
			return eligibilityOf(CARD_2);
		
		default:
			return eligibilityOf();
		}
	}

	private Eligibility eligibilityOf(String... cards) {
		
		EligibilityBuilder builder = newEligibility(cards.length);
		
		Stream. <String> of(cards).forEach(card -> builder.addCard(card));
		
		return builder.build();
	}
}

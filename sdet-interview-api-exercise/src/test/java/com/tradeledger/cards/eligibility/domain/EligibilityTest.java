package com.tradeledger.cards.eligibility.domain;

import static com.tradeledger.cards.eligibility.domain.Eligibility.newEligibility;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.tradeledger.cards.eligibility.domain.Eligibility;
import org.junit.jupiter.api.Test;

class EligibilityTest {

	@Test
	void testNewEligibility() {
		
		Eligibility eligibility = newEligibility(2)
									.addCard("1")
									.addCard("2")
									.build();
		
		assertThat(eligibility.getEligibleCards().size()).isEqualTo(2);
		assertThat(eligibility.getEligibleCards()).contains("1", "2");
	}

	@Test
	void testAddingLessCardsThrowsException() {
		
		assertThrows(IllegalStateException.class, () -> {
			
			newEligibility(2).addCard("1").build();
		});
		
	}
	
	@Test
	void testAddingMoreCardsThanEligibleThrowsException() {
		
		assertThrows(IllegalStateException.class, () -> {
			
			newEligibility(2)
				.addCard("1")
				.addCard("2")
				.addCard("3")
				.build();
		});
	}
}

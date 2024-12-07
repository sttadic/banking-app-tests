package ie.atu.sw;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountTest {
	private Account acc;
	
	@BeforeEach
	void setUp() {
		this.acc = new Account("Stjepan", 1000);
	}

	@Test
	void testConstructor() {
		assertEquals("Stjepan", acc.getAccountHolder());
		assertEquals(1000, acc.getBalance());
		assertEquals(0, acc.getLoan());
	}

}

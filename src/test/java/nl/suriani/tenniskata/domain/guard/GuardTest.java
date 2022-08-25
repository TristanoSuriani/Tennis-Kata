package nl.suriani.tenniskata.domain.guard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuardTest {

	@Test
	void isNotNull() {
		assertDoesNotThrow(() -> Guard.isNotNull(1));
		assertThrows(MissingValueException.class,
				() -> Guard.isNotNull(null));
	}

	@Test
	void greaterThanOrEqualsZero() {
		assertDoesNotThrow(() -> Guard.greaterThanOrEqualsZero(1));
		assertDoesNotThrow(() -> Guard.greaterThanOrEqualsZero(0));
		assertThrows(NegativeValueException.class,
				() -> Guard.greaterThanOrEqualsZero(-1));
	}
}

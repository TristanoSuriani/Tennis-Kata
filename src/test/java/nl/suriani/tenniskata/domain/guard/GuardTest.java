package nl.suriani.tenniskata.domain.guard;

import org.junit.jupiter.api.Test;

import java.util.List;

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

	@Test
	void isNotEmpty() {
		assertDoesNotThrow(() -> Guard.isNotEmpty(List.of(1)));
		assertThrows(EmptyListException.class, () -> Guard.isNotEmpty(List.of()));
	}
}

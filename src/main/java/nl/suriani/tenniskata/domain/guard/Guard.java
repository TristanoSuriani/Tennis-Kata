package nl.suriani.tenniskata.domain.guard;

import java.util.List;

public class Guard {
	public static void isNotNull(Object o) {
		if (o == null) {
			throw new MissingValueException();
		}
	}

	public static void greaterThanOrEqualsZero(Integer i) {
		if (i < 0) {
			throw new NegativeValueException(i);
		}
	}

	public static void hasMinimumLength(String s, int length) {
		greaterThanOrEqualsZero(length);
		if (s.length() < length) {
			throw new MalformedStringValueException("value should be " +
					length + " charachters long, but instead it is " +
					s.length() + ".");
		}
	}

	public static <T> void isNotEmpty(List<T> l) {
		if (l.isEmpty()) {
			throw new EmptyListException();
		}
	}
}

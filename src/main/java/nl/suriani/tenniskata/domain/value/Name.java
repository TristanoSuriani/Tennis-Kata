package nl.suriani.tenniskata.domain.value;

import nl.suriani.tenniskata.domain.guard.Guard;

public final class Name extends ValueType<String> {

	public Name(String value) {
		super(value);
		Guard.hasMinimumLength(value, 2);
	}
}

package nl.suriani.tenniskata.domain.value;

import nl.suriani.tenniskata.domain.guard.Guard;

public class ValueType<T> {
	public final T value;

	public ValueType(T value) {
		Guard.isNotNull(value);
		this.value = value;
	}

	public T value() {
		return value;
	}
}

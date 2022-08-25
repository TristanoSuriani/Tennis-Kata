package nl.suriani.tenniskata.domain.value;

import nl.suriani.tenniskata.domain.guard.Guard;

public class InstantId extends ValueType<Integer> {
	public InstantId(Integer value) {
		super(value);
		Guard.greaterThanOrEqualsZero(value);
	}
}

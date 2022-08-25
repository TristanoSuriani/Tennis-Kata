package nl.suriani.tenniskata.domain.value;

import nl.suriani.tenniskata.domain.guard.Guard;

public class SetPoints extends ValueType<Integer> {
	public SetPoints(Integer value) {
		super(value);
		Guard.greaterThanOrEqualsZero(value);
	}
}

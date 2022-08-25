package nl.suriani.tenniskata.domain.value;

import nl.suriani.tenniskata.domain.guard.Guard;

public class MatchPoints extends ValueType<Integer> {
	public MatchPoints(Integer value) {
		super(value);
		Guard.greaterThanOrEqualsZero(value);
	}
}

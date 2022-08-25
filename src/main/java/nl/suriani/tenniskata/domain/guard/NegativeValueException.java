package nl.suriani.tenniskata.domain.guard;

public class NegativeValueException extends DomainValidationException {
	public NegativeValueException(int value) {
		super("value is supposed to be >= 0 but it is '" + value + "'.");
	}
}

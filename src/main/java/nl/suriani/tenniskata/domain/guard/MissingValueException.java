package nl.suriani.tenniskata.domain.guard;

public class MissingValueException extends DomainValidationException {
	public MissingValueException() {
		super("value is missing.");
	}
}

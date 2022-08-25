package nl.suriani.tenniskata.domain.guard;

public class MalformedStringValueException extends DomainValidationException {
	public MalformedStringValueException(String message) {
		super(message);
	}
}

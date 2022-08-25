package nl.suriani.tenniskata.domain.guard;

public abstract class DomainValidationException extends RuntimeException {
	public DomainValidationException(String message) {
		super(message);
	}
}

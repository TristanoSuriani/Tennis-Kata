package nl.suriani.tenniskata.domain.guard;

public class EmptyListException extends DomainValidationException {
	public EmptyListException() {
		super("list is empty.");
	}
}

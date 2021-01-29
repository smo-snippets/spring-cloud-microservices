package de.smotastic;

/**
 * A UseCase, to execute certain business logic.
 *
 * @param <T> the return type of the usecase
 * @param <C> the parameter type of the usecase
 */
public interface UseCase<T, C extends UseCaseCommand> {
	T execute(C command);
}

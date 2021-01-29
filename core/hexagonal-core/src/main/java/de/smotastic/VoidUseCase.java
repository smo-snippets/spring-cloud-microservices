package de.smotastic;

/**
 * A {@link UseCase} with no return type
 * @param <C> the parameter type of the usecase
 */
public interface VoidUseCase<C extends UseCaseCommand> extends UseCase<Void, C> {

}

package fr.niware.serverapi.commons.database.throwing;

@FunctionalInterface
public interface ThrowingFunction<T, R> {
    R apply(T t) throws Exception;
}

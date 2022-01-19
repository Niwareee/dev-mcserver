package fr.niware.serverapi.commons.database.throwing;

public interface ThrowingConsumer<T> {
    void accept(T t) throws Exception;
}

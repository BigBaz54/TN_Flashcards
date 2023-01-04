package eu.telecomnancy.model.compact;

public interface Compact<T> {
    public Compact<T> from(T t);

    public T to();
}

package behavioural.iterator.utils;

public interface Aggregate<T> {
    Iterator<T> createIterator();
}

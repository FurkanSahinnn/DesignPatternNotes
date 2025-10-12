package behavioural.observer.subject;

import behavioural.observer.observer.IObserver;

public interface ISubject {
    void registerObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObserver();
}
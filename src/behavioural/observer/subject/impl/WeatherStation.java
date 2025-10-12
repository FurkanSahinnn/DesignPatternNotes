package behavioural.observer.subject.impl;

import behavioural.observer.observer.IObserver;
import behavioural.observer.subject.ISubject;

import java.util.ArrayList;
import java.util.List;

public class WeatherStation implements ISubject {
    private List<IObserver> observerList;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherStation() {
        this.observerList = new ArrayList<IObserver>();
    }

    @Override
    public void registerObserver(IObserver observer) {
        observerList.add(observer);
        System.out.println("Yeni observer ekledi.");
    }

    @Override
    public void removeObserver(IObserver observer) {
        observerList.remove(observer);
        System.out.println("Observer silindi.");
    }

    @Override
    public void notifyObserver() {
        observerList.forEach(observer -> observer.update(temperature, humidity, pressure));
    }

    // Hava durumunu değiştir ve bu değişikliği observer'lara bildir.
    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObserver();
    }
}

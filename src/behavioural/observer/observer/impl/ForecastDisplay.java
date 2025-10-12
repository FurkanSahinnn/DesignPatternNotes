package behavioural.observer.observer.impl;

import behavioural.observer.observer.IDisplay;
import behavioural.observer.observer.IObserver;

public class ForecastDisplay implements IObserver, IDisplay {
    private float currentPressure = 1013.0f;
    private float lastPressure;

    @Override
    public void update(float temperature, float humidity, float pressure) {
        lastPressure = currentPressure;
        currentPressure = pressure;
        display();
    }

    @Override
    public final void display() {
        System.out.print("Tahmin: ");
        if (currentPressure > lastPressure) {
            System.out.println("Hava düzeliyor!");
        } else if (currentPressure == lastPressure) {
            System.out.println("Hava durumu aynı kalacak");
        } else {
            System.out.println("Yağmur geliyor!");
        }
    }
}

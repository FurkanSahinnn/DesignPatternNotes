package behavioural.observer.observer.impl;

import behavioural.observer.observer.IDisplay;
import behavioural.observer.observer.IObserver;

public class CurrentConditionsDisplay implements IObserver, IDisplay {
    private float temperature;
    private float humidity;
    private float pressure;

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    @Override
    public final void display() {
        System.out.println("Sıcaklık (derece): " + temperature
                + ", Nem: " + humidity
                + ", Basınç: " + pressure);
    }
}

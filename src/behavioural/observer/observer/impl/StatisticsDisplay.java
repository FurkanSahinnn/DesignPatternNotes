package behavioural.observer.observer.impl;

import behavioural.observer.observer.IDisplay;
import behavioural.observer.observer.IObserver;

public class StatisticsDisplay implements IObserver, IDisplay {
    private float maxTemp = Float.MIN_VALUE;
    private float minTemp = Float.MAX_VALUE;
    private float tempSum = 0.0f;
    private int numReadings = 0;

    @Override
    public void update(float temperature, float humidity, float pressure) {
        tempSum += temperature;
        numReadings++;

        if (temperature > maxTemp) {
            maxTemp = temperature;
        }
        if (temperature < minTemp) {
            minTemp = temperature;
        }

        display();
    }

    @Override
    public final void display() {
        float avgTemp = tempSum / numReadings;
        System.out.println("İstatistikler - Ort: " + avgTemp + "°C, " +
                "Max: " + maxTemp + "°C, Min: " + minTemp + "°C");
    }
}

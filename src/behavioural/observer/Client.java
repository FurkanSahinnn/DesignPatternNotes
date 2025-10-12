package behavioural.observer;

import behavioural.observer.observer.impl.CurrentConditionsDisplay;
import behavioural.observer.observer.impl.ForecastDisplay;
import behavioural.observer.observer.impl.StatisticsDisplay;
import behavioural.observer.subject.impl.WeatherStation;

public class Client {
    public static void main(String[] args) {

        // Subject
        WeatherStation weatherStation = new WeatherStation();

        // Observer'lar
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay();
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay();
        ForecastDisplay forecastDisplay = new ForecastDisplay();

        // Observer'ları subject'e abone yap.
        weatherStation.registerObserver(currentConditionsDisplay);
        weatherStation.registerObserver(statisticsDisplay);
        weatherStation.registerObserver(forecastDisplay);

        System.out.println("\n=== İlk Ölçüm ===");
        weatherStation.setMeasurements(25.0f, 65.0f, 1013.0f);

        System.out.println("\n=== İkinci Ölçüm ===");
        weatherStation.setMeasurements(27.0f, 70.0f, 1012.0f);

        System.out.println("\n=== Üçüncü Ölçüm ===");
        weatherStation.setMeasurements(23.0f, 80.0f, 1010.0f);
    }
}

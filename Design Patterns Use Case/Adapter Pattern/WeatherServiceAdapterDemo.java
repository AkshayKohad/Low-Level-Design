interface WeatherService {
    double getTemperatureInCelsius(String city);
}

class ModernWeatherService implements WeatherService {
    @Override
    public double getTemperatureInCelsius(String city) {
        validateCity(city);
        return 30.0;
    }

    private void validateCity(String city) {
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("City cannot be blank");
        }
    }
}

class LegacyWeatherProvider {
    public double getTemperatureInFahrenheit(String city) {
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("City cannot be blank");
        }
        return 86.0;
    }
}

class LegacyWeatherAdapter implements WeatherService {
    private final LegacyWeatherProvider legacyWeatherProvider;

    public LegacyWeatherAdapter(LegacyWeatherProvider legacyWeatherProvider) {
        if (legacyWeatherProvider == null) {
            throw new IllegalArgumentException("Legacy weather provider cannot be null");
        }
        this.legacyWeatherProvider = legacyWeatherProvider;
    }

    @Override
    public double getTemperatureInCelsius(String city) {
        double temperatureInFahrenheit = legacyWeatherProvider.getTemperatureInFahrenheit(city);
        return (temperatureInFahrenheit - 32) * 5 / 9;
    }
}

class WeatherDashboard {
    private WeatherService weatherService;

    public void setWeatherService(WeatherService weatherService) {
        if (weatherService == null) {
            throw new IllegalArgumentException("Weather service cannot be null");
        }
        this.weatherService = weatherService;
    }

    public void showTemperature(String city) {
        if (weatherService == null) {
            throw new IllegalStateException("Select a weather service first");
        }
        double temperature = weatherService.getTemperatureInCelsius(city);
        System.out.println(city + " temperature: " + temperature + "°C");
    }
}

public class WeatherServiceAdapterDemo {
    public static void main(String[] args) {
        WeatherDashboard dashboard = new WeatherDashboard();

        dashboard.setWeatherService(new ModernWeatherService());
        dashboard.showTemperature("Mumbai");

        dashboard.setWeatherService(new LegacyWeatherAdapter(new LegacyWeatherProvider()));
        dashboard.showTemperature("Mumbai");
    }
}

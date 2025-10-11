package com.example.api_aggregator.response;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

@Data
public class WeatherResponse {
    private double latitude;
    private double longitude;
    private CurrentWeather current_weather;

    @Data
    public static class CurrentWeather {
        private double temperature;
        private double windspeed;
        private double winddirection;
        private int weathercode;
        private String time;
    }
}

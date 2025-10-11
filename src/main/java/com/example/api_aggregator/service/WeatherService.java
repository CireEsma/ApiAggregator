package com.example.api_aggregator.service;

import com.example.api_aggregator.response.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WebClient webClient;

    public Mono<WeatherResponse> getCurrentWeather(double latitude, double longitude) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/forecast")
                        .queryParam("latitude", latitude)
                        .queryParam("longitude", longitude)
                        .queryParam("current_weather", "true")
                        .build())
                .retrieve()
                .bodyToMono(WeatherResponse.class);
    }

    public Mono<String> getWeatherSentence(double latitude, double longitude) {
        return getCurrentWeather(latitude, longitude)
                .map(weather -> {
                    double temperature = weather.getCurrent_weather().getTemperature();
                    double windspeed = weather.getCurrent_weather().getWindspeed();

                    return String.format(
                            "Aujourd'hui à la latitude %.2f et longitude %.2f, il fait %.1f°C avec un vent de %.1f km/h.",
                            weather.getLatitude(),
                            weather.getLongitude(),
                            temperature,
                            windspeed
                    );
                });
    }
}

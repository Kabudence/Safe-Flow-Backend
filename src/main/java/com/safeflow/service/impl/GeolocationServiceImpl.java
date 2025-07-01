package com.safeflow.service.impl;


import com.safeflow.service.GeolocationService;
import com.safeflow.service.dto.ReverseGeocodingResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GeolocationServiceImpl implements GeolocationService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String getDisplayNameFromCoordinates(double latitude, double longitude) {
        String url = UriComponentsBuilder
                .fromHttpUrl("https://nominatim.openstreetmap.org/reverse")
                .queryParam("lat", latitude)
                .queryParam("lon", longitude)
                .queryParam("format", "json")
                .build()
                .toUriString();

        // Header obligatorio: User-Agent
        var headers = new org.springframework.http.HttpHeaders();
        headers.set("User-Agent", "safeflow-app");

        var entity = new org.springframework.http.HttpEntity<>(headers);

        var response = restTemplate.exchange(
                url,
                org.springframework.http.HttpMethod.GET,
                entity,
                ReverseGeocodingResponse.class
        );

        return response.getBody() != null ? response.getBody().getDisplayName() : "Desconocido";
    }
}

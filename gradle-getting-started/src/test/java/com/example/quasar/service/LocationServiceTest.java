package com.example.quasar.service;

import com.meli.quasar.services.LocationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LocationServiceTest {

    @Autowired
    private LocationService locationService;

    private double[] position;

    @Test
    public void when_send_tree_position_is_ok() {
        double[][] positions = new double[][]{{1.0}, {2.0}, {3.0}};
        double[] distances = new double[]{1.1, 0.1, 0.9};
        double[] expectedPosition = new double[]{2.1};
        position = locationService.getLocation(positions, distances);
        for (int i = 0; i < position.length; i++) {
            assertEquals(expectedPosition[i], position[i]);
        }
    }

    @Test
    public void when_send_position_is_ok() {
        double[][] positions = new double[][]{{-500.0, -200.0}, {100.0, -100.0}, {500.0, 100.0}};
        double[] distances = new double[]{100.0, 115.5, 142.7};
        double[] expectedPosition = new double[]{-58.315252587138595, -69.55141837312165};
        position = locationService.getLocation(positions, distances);
        for (int i = 0; i < position.length; i++) {
            assertEquals(expectedPosition[i], position[i]);
        }
    }

    @Test
    public void when_send_four_distances_is_ok() {
        double[][] positions = new double[][]{{5.0, -6.0}, {13.0, -15.0}, {21.0, -3.0}, {12.42, -21.2}};
        double[] distances = new double[]{8.06, 13.97, 23.32, 15.31};
        double[] expectedPosition = new double[]{-0.6, -11.8};
        double acceptedDelta = 1.0;
        position = locationService.getLocation(positions, distances);
        for (int i = 0; i < position.length; i++) {
            assertEquals(expectedPosition[i], position[i], acceptedDelta);
        }
    }
}

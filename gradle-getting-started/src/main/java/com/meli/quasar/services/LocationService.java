package com.meli.quasar.services;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.meli.quasar.dto.Position;
import com.meli.quasar.dto.Satellite;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Value("${position.kenobi}")
    private double[] positionKenobi;

    @Value("${position.skywalker}")
    private double[] positionSkywalker;

    @Value("${position.sato}")
    private double[] positionSato;

    public double[] getLocation(double[][] positions, double[] distances) {

        TrilaterationFunction trilaterationFunction = new TrilaterationFunction(positions, distances);
        NonLinearLeastSquaresSolver nSolver = new NonLinearLeastSquaresSolver(trilaterationFunction, new LevenbergMarquardtOptimizer());

        return nSolver.solve().getPoint().toArray();
    }

    public double[] getDistances(List<Satellite> satellites) {
        double[] distances = new double[satellites.size()];
        for (int i = 0; i < satellites.size(); i++) {
            distances[i] = satellites.get(i).getDistance();
        }
        return distances;
    }

    public double[][] getPositions(List<Satellite> satellites) {
        double[][] positions = new double[satellites.size()][];
        positions[0] = positionKenobi;
        positions[1] = positionSkywalker;
        positions[2] = positionSato;

        return positions;
    }

    public Position setLocation(double[] points) {
        Position position = new Position();
        position.setX(points[0]);
        position.setY(points[1]);
        return position;
    }

}

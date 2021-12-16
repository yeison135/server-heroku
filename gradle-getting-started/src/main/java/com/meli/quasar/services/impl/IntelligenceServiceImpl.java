package com.meli.quasar.services.impl;

import com.meli.quasar.dto.Position;
import com.meli.quasar.dto.PositionShip;
import com.meli.quasar.dto.Ship;
import com.meli.quasar.exceptions.LocationException;
import com.meli.quasar.exceptions.MessageException;
import com.meli.quasar.services.IntelligenceService;
import com.meli.quasar.services.LocationService;
import com.meli.quasar.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntelligenceServiceImpl implements IntelligenceService {

    @Autowired
    public IntelligenceServiceImpl(MessageService messageService, LocationService locationService) {
        this.messageService = messageService;
        this.locationService = locationService;
    }

    public final MessageService messageService;

    public final LocationService locationService;

    @Override
    public PositionShip getTopSecret(Ship ship) throws MessageException, LocationException {

        PositionShip positionShip = new PositionShip();
        List<List<String>> messages = messageService.getMessages(ship.getSatellites());
        if (ship.getSatellites().size() < 2)
            throw new MessageException("No se pudo traducir el mensaje");

        double[] points = locationService.getLocation(locationService.getPositions(ship.getSatellites()), locationService.getDistances(ship.getSatellites()));

        if(points.length < 1)
            throw new LocationException("No se pudo optener la localización");

        Position position = locationService.setLocation(points);
        positionShip.setPosition(position);
        String world = messageService.getMessage(messages);
        positionShip.setMessage(world);
        return positionShip;
    }
}

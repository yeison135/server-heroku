package com.meli.quasar.services;


import com.meli.quasar.dto.PositionShip;
import com.meli.quasar.dto.Ship;
import com.meli.quasar.exceptions.LocationException;
import com.meli.quasar.exceptions.MessageException;
import org.springframework.stereotype.Service;

@Service
public interface IntelligenceService {

   PositionShip getTopSecret(Ship ship) throws MessageException, LocationException;

}

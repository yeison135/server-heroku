package com.meli.quasar.controller;


import com.meli.quasar.dto.PositionShip;
import com.meli.quasar.dto.Ship;
import com.meli.quasar.exceptions.LocationException;
import com.meli.quasar.exceptions.MessageException;
import com.meli.quasar.services.IntelligenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/quasar")
public class MessageController {

    @Autowired
    public MessageController(IntelligenceService intelligenceService) {
        this.intelligenceService = intelligenceService;
    }

    private final IntelligenceService intelligenceService;

    @PostMapping(value = "/topSecret", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PositionShip> topSecret(RequestEntity<Ship> requestEntity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(intelligenceService.getTopSecret(requestEntity.getBody()));
        } catch (MessageException | LocationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

}

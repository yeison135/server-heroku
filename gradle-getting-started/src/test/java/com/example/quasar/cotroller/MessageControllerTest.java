package com.example.quasar.cotroller;

import com.meli.quasar.controller.MessageController;
import com.meli.quasar.dto.Position;
import com.meli.quasar.dto.PositionShip;
import com.meli.quasar.dto.Ship;
import com.meli.quasar.exceptions.LocationException;
import com.meli.quasar.exceptions.MessageException;
import com.meli.quasar.services.IntelligenceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

import static org.mockito.Mockito.when;

@SpringBootTest
public class MessageControllerTest {

    @Autowired
    private MessageController messageController;

    @MockBean
    private IntelligenceService intelligenceService;

    @Test
    public void when_send_position_ok() throws MessageException, URISyntaxException, LocationException {

        PositionShip positionShip = new PositionShip();
        positionShip.setMessage("Este es una mensaje secreto");
        Position position = new Position();
        position.setX(1.5);
        position.setY(23.5);
        positionShip.setPosition(position);
        when(intelligenceService.getTopSecret(new Ship())).thenReturn(positionShip);
        String url = "url";
        RequestEntity<Ship> requestEntity;
        requestEntity = RequestEntity.put(new URI(url)).contentType(MediaType.APPLICATION_JSON).body(new Ship());

        ResponseEntity<PositionShip> test = messageController.topSecret(requestEntity);
        Assertions.assertEquals(test.getBody().getMessage(), positionShip.getMessage());
    }


}

package com.meli.quasar.dto;

import lombok.Data;

import java.util.List;

@Data
public class Satellite {

    String name;

    Double distance;

    List<String> message;

}

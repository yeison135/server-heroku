package com.example.quasar.service;


import com.meli.quasar.services.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MessageServiceTest {

    @Autowired
    private MessageService messageService;

    @Test
    public void when_send_information_with_all_satellites_is_ok() {
        List<List<String>> messages = new ArrayList<>();
        String[] messageOne = {"este", "", "un", "", ""};
        String[] messageTwo = {"este", "", "", "mensaje", ""};
        String[] messageTree = {"", "es", "", "", "secreto"};
        messages.add(Arrays.stream(messageOne).collect(Collectors.toList()));
        messages.add(Arrays.stream(messageTwo).collect(Collectors.toList()));
        messages.add(Arrays.stream(messageTree).collect(Collectors.toList()));
        String expectedMsg = "este es un mensaje secreto ";
        assertEquals(messageService.getMessage(messages), expectedMsg);
    }

    @Test
    public void when_send_information_with_more_tree_satellites_is_ok() {
        List<List<String>> messages = new ArrayList<>();
        String[] messageOne = {"este", "", "un", "", ""};
        String[] messageTwo = {"este", "", "", "mensaje", ""};
        String[] messageFour = {"", "es", "", "", "secreto"};
        String[] messageTree = {"", "es", "", "", "secreto"};
        messages.add(Arrays.stream(messageOne).collect(Collectors.toList()));
        messages.add(Arrays.stream(messageTwo).collect(Collectors.toList()));
        messages.add(Arrays.stream(messageTree).collect(Collectors.toList()));
        messages.add(Arrays.stream(messageFour).collect(Collectors.toList()));
        String expectedMsg = "este es un mensaje secreto ";
        assertEquals(messageService.getMessage(messages), expectedMsg);
    }

}

package com.meli.quasar.services;

import com.meli.quasar.dto.Satellite;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {


    public String getMessage(List<List<String>> messages) {
        List<String> words = getWords(messages);
        for (List<String> msg : messages) {
            for (int i = 0; i < words.size(); i++) {
                if (!msg.get(i).isEmpty())
                    words.set(i, msg.get(i));
            }
        }
        return splitMessage(words);
    }

    public List<List<String>> getMessages(List<Satellite> satellites) {
        List<List<String>> messages = new ArrayList<>();
        for (Satellite satellite : satellites) {
            messages.add(satellite.getMessage());
        }
        return messages;
    }

    private List<String> getWords(List<List<String>> messages) {
        List<String> words = new ArrayList<>();
        int length = messages.get(0).get(0).length();
        for (int i = 0; i <= length; i++) {
            words.add("");
        }
        return words;
    }

    private String splitMessage(List<String> words) {
        StringBuilder wordReturn = new StringBuilder();
        words.forEach(word -> wordReturn.append(word).append(" "));
        return wordReturn.toString();
    }

}

package day16.workshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import day16.workshop.model.Mastermind;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

@RestController
@RequestMapping(path="/api/boardgame", consumes = MediaType.APPLICATION_JSON_VALUE, 
produces = MediaType.APPLICATION_JSON_VALUE)
public class BoardGameController {

    @PostMapping
    public ResponseEntity<String> createBoardGame(@RequestBody Mastermind ms) {

        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("mastermind", ms.toJSON());
        JsonObject result = builder.build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());

        //TODO replace body parameter
    }


    
}

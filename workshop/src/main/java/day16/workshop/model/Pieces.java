package day16.workshop.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Pieces implements Serializable {

    private DecodingBoard decoding_board;
    private Pegs pegs;
    private Rulebook rulebook;

    public DecodingBoard getDecoding_board() {
        return decoding_board;
    }
    public void setDecoding_board(DecodingBoard decoding_board) {
        this.decoding_board = decoding_board;
    }

    public Pegs getPegs() {
        return pegs;
    }
    public void setPegs(Pegs pegs) {
        this.pegs = pegs;
    }
    public Rulebook getRulebook() {
        return rulebook;
    }
    public void setRulebook(Rulebook rulebook) {
        this.rulebook = rulebook;
    }

    public JsonObject toJSON() {

        String dbString = this.getDecoding_board().toJSON().toString();
        String pegsString = this.getPegs().toJSON().toString();
        String rbString = this.getRulebook().toJSON().toString();

        return Json.createObjectBuilder()
        .add("decoding_board", dbString)
        .add("pegs", pegsString)
        .add("rulebook", rbString)
        .build();
    }


    
    
}

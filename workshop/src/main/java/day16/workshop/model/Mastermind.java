package day16.workshop.model;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;

public class Mastermind implements Serializable{

    private String name;
    //TODO
    private Pieces pieces;
    private String id;
    private int insert_count;
    private int update_count;
    private Boolean upSert = false;

    public Mastermind() {
        this.id = generateId(8);
    }

    private synchronized String generateId(int numChars) {
        Random random = new Random();
        StringBuilder strBuilder = new StringBuilder();
        int nextVal = random.nextInt();
        while(strBuilder.length() < 8) {
            strBuilder.append(Integer.toHexString(nextVal));
        }

        return strBuilder.toString().substring(0, numChars);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Pieces getPieces() {
        return pieces;
    }
    public void setPieces(Pieces pieces) {
        this.pieces = pieces;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
   
    public Boolean getUpSert() {
        return upSert;
    }
    public void setUpSert(Boolean upSert) {
        this.upSert = upSert;
    }

    
    
    public int getInsert_count() {
        return insert_count;
    }

    public void setInsert_count(int insert_count) {
        this.insert_count = insert_count;
    }

    public int getUpdate_count() {
        return update_count;
    }

    public void setUpdate_count(int update_count) {
        this.update_count = update_count;
    }

    public JsonObject toJSON() {

        String piecesString = this.getPieces().toJSON().toString();

        return Json.createObjectBuilder()
        .add("name", this.getName())
        .add("pieces", piecesString)
        .add("id", this.getId())
        .add("insertCount", this.getInsert_count())
        .add("updateCount", this.update_count)
        .add("isUpsert", this.getUpSert())
        .build();
    }



    
    
}

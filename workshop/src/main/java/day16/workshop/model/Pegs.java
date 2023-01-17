package day16.workshop.model;

import java.io.Serializable;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Pegs implements Serializable {

    private int total_count;
    private List<Type> types;


    public List<Type> getTypes() {
        return types;
    }
    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public int getTotal_count() {
        return total_count;
    }
    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    

    public JsonObject toJSON() {

        List<JsonObject> typelist = this.getTypes().stream()
        .map(t -> t.toJSON())
        .toList();

        return Json.createObjectBuilder()
        .add("total_count", this.getTotal_count())
        .add("types", typelist.toString())
        .build();
    }



    
    
}

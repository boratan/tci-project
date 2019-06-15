package serializers;

import com.google.gson.Gson;
import models.GetOne;

public class GetOneSerializer implements IGenericSerializer<GetOne> {
    private Gson gson;

    public GetOneSerializer() { gson = new Gson(); }

    @Override
    public String serializeToJson(GetOne object) {
        if(object == null) {
            throw new IllegalArgumentException();
        }
        return gson.toJson(object);
    }

    @Override
    public GetOne deserializeFromJson(String json) {
        if(json == null || json == "") {
            throw new IllegalArgumentException();
        }
        return gson.fromJson(json, GetOne.class);
    }
}

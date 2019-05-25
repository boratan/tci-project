package serializers;

import com.google.gson.Gson;
import models.*;

import java.util.List;

public class GetAllSerializer implements IGenericSerializer<GetAll> {
    private Gson gson;

    public Gson getGson() {
        return gson;
    }

    public GetAllSerializer() { gson = new Gson(); }

    @Override
    public String serializeToJson(GetAll object) {
        return null;
    }

    @Override
    public GetAll deserializeFromJson(String json) {
        return null;
    }
}

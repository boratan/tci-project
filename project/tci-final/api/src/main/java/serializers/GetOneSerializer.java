package serializers;

import com.google.gson.Gson;
import models.GetOne;

public class GetOneSerializer implements IGenericSerializer<GetOne> {
    private Gson gson;

    public Gson getGson() {
        return gson;
    }

    public GetOneSerializer() { gson = new Gson(); }

    @Override
    public String serializeToJson(GetOne object) {
        return null;
    }

    @Override
    public GetOne deserializeFromJson(String json) {
        return null;
    }
}

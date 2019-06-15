package serializers;

import com.google.gson.Gson;
import models.*;

public class GetAllSerializer implements IGenericSerializer<GetAll> {
    private Gson gson;

    public Gson getGson() {
        return gson;
    }

    public GetAllSerializer() {
        gson = new Gson();
    }

    @Override
    public String serializeToJson(GetAll object) {
        if(object == null)
            throw new IllegalArgumentException();
        else{
            String jsonString;
            jsonString = this.getGson().toJson(object);
            return jsonString;
        }
    }

    @Override
    public GetAll deserializeFromJson(String json) {
        if(json == null || json.equals("")) throw new IllegalArgumentException();
        else{
            GetAll getAll;
            getAll = this.getGson().fromJson(json, GetAll.class);
            return  getAll;
        }
    }
}

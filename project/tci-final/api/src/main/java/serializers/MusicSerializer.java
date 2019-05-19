package serializers;

import models.Music;

public class MusicSerializer implements IGenericSerializer<Music> {

    /**
     * Serializes the Music model to JSON format.
     * @param object
     * @return
     */
    @Override
    public String serializeToJson(Music object) {
        return null;
    }

    /**
     * De-serializes JSON string to Music model.
     * @param json
     * @return
     */
    @Override
    public Music deserializeFromJson(String json) {
        return null;
    }
}

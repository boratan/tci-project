import models.RequestInfo;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.xml.ws.Response;

public class ApiMain {

    LogicMain businessLogic;

    /**
     * Uses the businessLogic to get all scraped data from a base url
     * and returns that data in JSON format.
     * @param url
     * @return
     */
    public Response getAll(String url) {
        throw new NotImplementedException();
    }

    /**
     * Uses the businessLogic to get a model with given type and extra data from a base url
     * and returns that model in JSON format.
     * @param url
     * @param type
     * @param extraInfo
     * @return
     */
    public Response getOne(String url, String type, String extraInfo) {
        throw new NotImplementedException();
    }

    /**
     * Returns the information about the last request performed.
     * If there is no such information, it returns code NotFound.
     * @return
     */
    public Response getLastRequest() {
        throw new NotImplementedException();
    }

    /**
     * Writes the information about the performed request to a file.
     * @param info
     */
    private void writeRequestInfoToFile(RequestInfo info) {
        throw new NotImplementedException();
    }

    /**
     * Reads from file where the information about the last request is stored
     * and return it in the format of RequestInfo model.
     * @return
     */
    private RequestInfo readFromFile() {
        throw new NotImplementedException();
    }
}

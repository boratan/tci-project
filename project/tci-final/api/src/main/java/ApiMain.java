import com.google.gson.Gson;
import com.google.gson.JsonElement;
import javafx.util.Pair;
import models.*;
import serializers.GetAllSerializer;
import serializers.GetOneSerializer;
import serializers.RequestInfoSerializer;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

@Path("api")
public class ApiMain {

    private LogicMain businessLogic;
    private RequestInfoSerializer requestInfoSerializer;
    private GetAllSerializer getAllSerializer;
    private GetOneSerializer getOneSerializer;
    private String fileName;
    private Gson gson;

    public ApiMain(){
        businessLogic = new LogicMain();
        requestInfoSerializer = new RequestInfoSerializer();
        getAllSerializer = new GetAllSerializer();
        getOneSerializer = new GetOneSerializer();
        fileName = "../lastRequest.json";
        gson = new Gson();
    }

    /**
     * Uses the businessLogic to get all scraped data from a base url
     * and returns that data in JSON format.
     * @param url
     * @return
     */
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("url") String url) {
        URL asUrl = null;
        try {
            asUrl = new URL(url);
        } catch (MalformedURLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Not a real url!").build();
        }
        Pair<RequestInfo, Set<IModel>> result = businessLogic.getAllFromUrl(asUrl);
        if (result.getValue().isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).entity("No records found!").build();
        }
        BaseRequest baseRequest = new BaseRequest();
        RequestInfo request = makeOfficialRequest(baseRequest, result.getKey());
        writeRequestInfoToFile(request);
        String requestInfo = requestInfoSerializer.serializeToJson(request);
        String models = getAllSerializer.serializeToJson(new GetAll(result.getValue()));
        return Response.ok(requestInfo + models).type(MediaType.APPLICATION_JSON).build();
    }

    /**
     * Uses the businessLogic to get a model with given type and extra data from a base url
     * and returns that model in JSON format.
     * @param url
     * @param type
     * @param extraInfo
     * @return
     */
    @GET
    @Path("one")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(@QueryParam("url") String url,@QueryParam("type") String type,@QueryParam("extraInfo") String extraInfo) {
        URL asUrl = null;
        try {
            asUrl = new URL(url);
        } catch (MalformedURLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Not a url!").build();
        }
        Pair<RequestInfo, Set<IModel>> result = businessLogic.getOneFromUrl(asUrl, type, extraInfo);
        if (result.getValue().isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).entity("No record found!").build();
        }
        BaseRequest baseRequest = new BaseRequest();
        RequestInfo request = makeOfficialRequest(baseRequest, result.getKey());
        writeRequestInfoToFile(request);
        String requestInfo = requestInfoSerializer.serializeToJson(request);
        String model = getOneSerializer.serializeToJson(new GetOne(result.getValue()));
        return Response.ok(requestInfo + model).build();
    }

    /**
     * Returns the information about the last request performed.
     * If there is no such information, it returns code NotFound.
     * @return
     */
    @GET
    @Path("last")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLastRequest() {
        String result;
        try {
            result = readFromFile();
        } catch (FileNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("No record found!").build();
        }
        return Response.ok(result).build();
    }

    /**
     * Writes the information about the performed request to a file.
     * @param info
     */
    private void writeRequestInfoToFile(RequestInfo info) {
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(requestInfoSerializer.serializeToJson(info));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads from file where the information about the last request is stored
     * and return it in the format of json String.
     * @return
     */
    private String readFromFile() throws FileNotFoundException {
        JsonElement json = gson.fromJson(new FileReader(fileName), JsonElement.class);
        return gson.toJson(json);
    }

    /**
     * Adds the id and the time to a RequestInfo form a BaseRequest.
     * @param baseRequest The object containing the id and time.
     * @param requestInfo The object that is modified.
     * @return A RequestInfo with set id and time.
     */
    private RequestInfo makeOfficialRequest(BaseRequest baseRequest, RequestInfo requestInfo){
        requestInfo.setId(baseRequest.getId());
        requestInfo.setTime((int) baseRequest.getTimeInMilli());
        return requestInfo;
    }
}

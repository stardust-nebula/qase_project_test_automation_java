package api.service;

import io.restassured.response.Response;
import model.Project;

import static api.util.ApiConstants.PROJECT_API_ENDPOINT;

public class ProjectApiService extends BaseApiService {
    public Response getAllProjects() {
        return get(PROJECT_API_ENDPOINT);
    }

    public Response createProject(Project project){
        return post(PROJECT_API_ENDPOINT, converter.toJson(project));
    }

}

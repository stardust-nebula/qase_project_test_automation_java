package io.qase.api;

import io.restassured.response.Response;
import io.qase.model.Project;

import static io.qase.util.ApiConstants.PROJECT_API_ENDPOINT;

public class ProjectApiService extends BaseApiService {
    public Response getAllProjects() {
        return get(PROJECT_API_ENDPOINT);
    }

    public Response createProject(Project project){
        return post(PROJECT_API_ENDPOINT, converter.toJson(project));
    }

}

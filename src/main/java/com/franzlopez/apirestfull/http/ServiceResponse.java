package com.franzlopez.apirestfull.http;

import com.franzlopez.apirestfull.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse {

    private String message;
    private Object body;

    public static ServiceResponse ok(Object body) {
        ServiceResponse response = new ServiceResponse();
        response.setMessage(Constants.MESSAGE_OK);
        response.setBody(body);
        return response;
    }

    public static ServiceResponse general(String message, Object body) {
        ServiceResponse response = new ServiceResponse();
        response.setMessage(message);
        response.setBody(body);
        return response;
    }


    public static ServiceResponse noContent(Object body) {
        ServiceResponse response = new ServiceResponse();
        response.setMessage(Constants.MESSAGE_NO_CONTENT);
        response.setBody(body);
        return response;
    }

    public static ServiceResponse badRequest(Object body) {
        ServiceResponse response = new ServiceResponse();
        response.setMessage(Constants.MESSAGE_BAD_REQUEST);
        response.setBody(body);
        return response;
    }

    public static ServiceResponse internalError(Object body) {
        ServiceResponse response = new ServiceResponse();
        response.setMessage(Constants.MESSAGE_INTERNAL_ERROR);
        response.setBody(body);
        return response;
    }
}

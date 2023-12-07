package com.customers.utils;

import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
@Setter
public class ApiResponse {
    private Object result;
    private Boolean success;
    private Object error;

     private static ApiResponse Success(Object result) {
       return new ApiResponse(result, true, null);
    }

    private static ApiResponse Failed(Object error) {
        return new ApiResponse(null, false, error);
    }

    public static ResponseEntity<ApiResponse> BadRequest(Object error)
    {
        var response = Failed(error);
        return ResponseEntity.badRequest().body(response);
    }

    public static ResponseEntity<ApiResponse> Ok(Object result)
    {
        var response = Success(result);
        return ResponseEntity.ok().body(response);
    }
}

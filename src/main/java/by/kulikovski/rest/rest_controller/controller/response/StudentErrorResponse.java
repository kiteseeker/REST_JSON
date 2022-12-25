package by.kulikovski.rest.rest_controller.controller.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentErrorResponse {

    // custom error response to be sent back to the client as JSON in case of error occurs

    private int status;
    private String message;
    private long timeStamp;
}

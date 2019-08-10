package app.api.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import app.api.handler.ErrorResponse;

/**
 * Error controller.
 * 
 * @author masafumi
 *
 */
@RestController
public class ApiErrorController implements ErrorController {

    /**
     * @param request http request
     * @return error entity
     */
    @RequestMapping("/error")
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        ErrorResponse body = new ErrorResponse(statusCode.toString(), exception.getMessage());
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (statusCode == HttpStatus.NOT_FOUND.value()) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<ErrorResponse>(body, headers, status);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}

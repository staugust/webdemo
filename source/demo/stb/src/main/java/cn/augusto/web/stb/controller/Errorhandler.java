package cn.augusto.web.stb.controller;

import org.assertj.core.util.Throwables;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

@Controller
public class Errorhandler implements ErrorController {

  /**
   * Display an error page, as defined in web.xml <code>custom-error</code> element.
   */
  @RequestMapping("/error")
  public String handleError(HttpServletRequest request, HttpServletResponse response, Model model) {
    // retrieve some useful information from the request
    Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
    Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
    // String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
    String exceptionMessage = getExceptionMessage(throwable, statusCode);

    String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
    if (requestUri == null) {
      requestUri = "Unknown";
    }

    String message = MessageFormat.format("{0} returned for {1} with message {2}",
        statusCode, requestUri, exceptionMessage
    );

    model.addAttribute("errorMessage", message);
    return "error";
  }

  @Override
  public String getErrorPath() {
    return "/error";
  }

  private String getExceptionMessage(Throwable throwable, Integer statusCode) {
    if (throwable != null) {
      return Throwables.getRootCause(throwable).getMessage();
    }
    HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
    return httpStatus.getReasonPhrase();
  }
}
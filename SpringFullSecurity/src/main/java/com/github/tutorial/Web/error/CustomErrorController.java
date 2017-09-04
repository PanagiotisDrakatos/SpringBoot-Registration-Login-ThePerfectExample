package com.github.tutorial.Web.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";
    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(value = PATH)
    public ModelAndView error(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ErrorJson error = new ErrorJson(response.getStatus(), getErrorAttributes(request, true));
        modelAndView.addObject("StatusType", "StatusType is : " + error.getStatus());
        modelAndView.addObject("ErrorType", "ErrorType is : " + error.getError());
        modelAndView.addObject("TraceType", "TraceType is : " + error.getTrace());
        modelAndView.addObject("TimeStampType", "TimeStampType is : " + error.getTimeStamp());
        modelAndView.addObject("MessageType", "MessageType is : " + error.getMessage());
        modelAndView.setViewName("accessDenied");
        return modelAndView;
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }


    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }
}

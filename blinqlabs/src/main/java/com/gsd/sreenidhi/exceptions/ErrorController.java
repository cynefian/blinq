package com.gsd.sreenidhi.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {
 
    @RequestMapping(value = "errors", method = RequestMethod.GET)
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {
         
        ModelAndView errorPage = new ModelAndView("error/error");
        String errorMsg = "";
        int httpErrorCode = getErrorCode(httpRequest);
 
        switch (httpErrorCode) {
            case 400: {
                errorMsg = "Something Doesn't Fit. Bad Request.";
                break;
            }
            case 401: {
                errorMsg = "Looks like you are not authorized to access this page.";
                break;
            }
            case 403: {
                errorMsg = "Hey! You're not supposed to be here..";
                break;
            }
            case 404: {
                errorMsg = "Looks like the aliens got our cow. We'll trace it out soon.";
                break;
            }
            case 405: {
                errorMsg = "Careful! You tried to do something that's not allowed.";
                break;
            }
            case 500: {
                errorMsg = "You broke our machine. Don't worry we'll fix it soon.";
                break;
            }
        }
        errorPage.addObject("errorMsg", errorMsg);
        errorPage.addObject("errorCode",httpErrorCode);
        return errorPage;
    }
     
    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
          .getAttribute("javax.servlet.error.status_code");
    }
}

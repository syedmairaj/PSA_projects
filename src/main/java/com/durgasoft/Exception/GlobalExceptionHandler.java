package com.durgasoft.Exception;


import com.durgasoft.payload.ErrMsg;
import org.apache.coyote.Request;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {



//@ExceptionHandler(ResourceNotFound.class)
////use request to get the client details before this i have created variables in dto to manipulate the output data.
//    public ResponseEntity<ErrMsg> handleEmpNotFound(ResourceNotFound e,WebRequest request){
//
//    //created object for dto ErrMsg to get the data from dto to global exception class, getDescription is java method
//   ErrMsg errMsg = new ErrMsg(new Date(), e.getMessage(),request.getDescription(true));
//
//    return new ResponseEntity<>(errMsg, HttpStatus.INTERNAL_SERVER_ERROR);
//
//
//    }

//    @ExceptionHandler(TestNotFound.class)
//
//    public ResponseEntity<ErrMsg> testNotFound(TestNotFound e,WebRequest request) {
//
//    ErrMsg errMsg = new ErrMsg(new Date(),e.getMessage(),request.getDescription(true));
//        return new ResponseEntity<>(errMsg , HttpStatus.NOT_FOUND);
//    }

//this exception handle anykind of exception except nullpointer exception
    @ExceptionHandler(Exception.class)

    public ResponseEntity<ErrMsg> testNotFound(Exception e,WebRequest request) {

        ErrMsg errMsg = new ErrMsg(new Date(),e.getMessage(),request.getDescription(true));
        return new ResponseEntity<>(errMsg , HttpStatus.NOT_FOUND);
    }
}



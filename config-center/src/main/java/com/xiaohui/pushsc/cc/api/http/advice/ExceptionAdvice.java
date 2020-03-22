package com.xiaohui.pushsc.cc.api.http.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiaohui
 * create on 2020-03-16
 */
@ControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ResponseBody
    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> unHandleException(final Exception ex, final HttpServletRequest request, final HttpServletResponse response) {

        log.error("接口调用 {} 发生异常 : {} ", request.getRequestURI(), ex.getMessage());
        log.error("stack Info", ex);

        return new ResponseEntity<>("backend error", HttpStatus.INTERNAL_SERVER_ERROR);

    }

}

package ru.fedusiv.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @After("@annotation(org.springframework.web.bind.annotation.GetMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.PostMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PutMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PatchMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void logRequest(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        StringBuilder message = new StringBuilder();

        if (method.getName().matches("get.*")) {
            message.append("<GET> ");
        } else if (method.getName().matches("add.*")
                    || method.getName().matches("upload.*")) {
            message.append("<POST> ");
        } else if (method.getName().matches("update.*")) {
            message.append("<PUT> ");
        } else if (method.getName().matches("patch.*")) {
            message.append("<PATCH> ");
        } else if (method.getName().matches("delete.*")) {
            message.append("<DELETE> ");
        }

        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();
        message.append(request.getRequestURL());
        log.info(message.toString());
    }

}

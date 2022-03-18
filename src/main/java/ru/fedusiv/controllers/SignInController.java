package ru.fedusiv.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.fedusiv.dto.SignUpForm;
import ru.fedusiv.exceptions.EntitySaveException;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.Objects;

@Controller
public class SignInController {

    @GetMapping("/signIn")
    public String signInGET() {
        return "sign_in";
    }



}

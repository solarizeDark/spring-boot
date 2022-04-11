package ru.fedusiv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.fedusiv.dto.SignUpForm;
import ru.fedusiv.dto.UserDto;
import ru.fedusiv.entities.User;
import ru.fedusiv.exceptions.EntitySaveException;
import ru.fedusiv.services.interfaces.SignUpService;
import ru.fedusiv.services.interfaces.UsersService;

import javax.validation.Valid;
import java.util.Objects;

@Controller
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/signUp", produces = "text/plain;charset=UTF-8")
    public String signUpGET(Model model) {
        model.addAttribute("signUpForm", new SignUpForm());
        return "sign_up";
    }

    @PostMapping(value = "/signUp")
    public String signUpPOST(@Valid SignUpForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {

            bindingResult.getAllErrors()
                    .stream()
                    .filter(error ->
                            Objects.requireNonNull(error.getCodes())[0].equals("signUpForm.UsernamePasswordNonEquality")
                    )
                    .forEach(error -> model.addAttribute("usernamePasswordEquality", error.getDefaultMessage()));

            model.addAttribute("signUpForm", form);
            return "sign_up";
        }

        signUpService.save(form);
        return "redirect:profile";
    }

    @PostMapping(value = "/signUp", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addUser(@Valid @RequestBody SignUpForm form, BindingResult bindingResult) throws EntitySaveException {

        StringBuilder response = new StringBuilder();

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors()
                    .forEach(error -> response.append(error.getDefaultMessage()).append("\n"));
            throw new EntitySaveException("User", response.toString());
        }

        signUpService.save(form);

        return new ResponseEntity<>("saved successfully", HttpStatus.OK);
    }

    // rest
    @PostMapping("/users/add")
    public ResponseEntity<String> addNewUser(@RequestBody UserDto userDto) {
        usersService.save(userDto);
        return new ResponseEntity<>("saved", HttpStatus.OK);
    }

    @GetMapping("/confirm/{uuid}")
    public ResponseEntity<String> confirmEmail(@PathVariable String uuid) {
        usersService.confirmAccount(uuid);
        return new ResponseEntity<>("confirmed", HttpStatus.OK);
    }


}

package ru.fedusiv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.fedusiv.dto.Bio;
import ru.fedusiv.entities.Student;
import ru.fedusiv.entities.User;
import ru.fedusiv.exceptions.EntitySaveException;
import ru.fedusiv.exceptions.NoEntityException;
import ru.fedusiv.facades.AuthenticationFacade;
import ru.fedusiv.security.UserDetailsImpl;
import ru.fedusiv.services.interfaces.StudentsService;
import ru.fedusiv.services.interfaces.UsersService;

@Controller
public class ProfilesController {

    @Autowired
    private StudentsService studentsService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @GetMapping("/profile")
    public String profileGET() {
        return "profile";
    }

    @PostMapping("/profile")
    public ModelAndView profilePOST(Bio bio) {

        UserDetailsImpl userDetails = (UserDetailsImpl) authenticationFacade.getAuthentication().getPrincipal();
        User user = userDetails.getUser();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile");
        try {
            Student student = studentsService.save(bio);
            user.setStudent(student);
//            usersService.save(user);
        } catch (NoEntityException | EntitySaveException exception) {
            modelAndView.addObject("error", exception.getMessage());
        }
        return modelAndView;
    }

}

package com.test.assignment.user.controller;

import com.test.assignment.user.application.dto.UserInformationDTO;
import com.test.assignment.user.application.service.UserInformationService;
import com.test.assignment.user.domain.model.WorkSector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Controller class providing GET and POST methods
 * to display and save User information respectively.
 *
 * @author Manish Gupta
 * @version $Id: UserInformationController.java 1.0
 * @since 2020-11-09
 */
@Controller
public class UserInformationController {

    private final UserInformationService userInformationService;
    private final HttpServletRequest httpServletRequest;

    @Autowired
    public UserInformationController(UserInformationService userInformationService, HttpServletRequest httpServletRequest) {
        this.userInformationService = userInformationService;
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * GET Method to get the user information from the database
     * and render the view using index.html
     *
     * @param model Model object that can be used to add Java objects and then pass
     *             to the view component to render the results in the index.html form
     * @return HTML form name
     */
    @GetMapping("/")
    public String getUserData(Model model) {
        UserInformationDTO userInformationDTO = userInformationService.fetchUserInformation(httpServletRequest.getRemoteUser());
        List<WorkSector> workSectors = userInformationService.getRootWorkSectors();
        model.addAttribute("sectors", workSectors);
        model.addAttribute("userInformationDTO", userInformationDTO);
        return "index";
    }

    /**
     * POST Method to save the User information provided via index.html form.
     *
     * @param userInformationDTO UserInformationDTO object containing User filled
     *                          form data which is also validated based on the
     *                           validation criteria defined in UserInformationDTO class.
     * @param bindingResult BindingResult object containing validation error information
     *                      in the User entered form data.
     * @return redirect to the GET method along with the success status and possibly error code in the URL
     */
    @PostMapping("/")
    public String submitUserInformation(@ModelAttribute("userInformationDTO") @Valid UserInformationDTO userInformationDTO, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors("name")) {
            return "redirect:/?success=false&error=1";
        }
        else if (bindingResult.hasFieldErrors("selectedSectors")) {
            return "redirect:/?success=false&error=2";
        }
        else if (bindingResult.hasFieldErrors("agreeToTermsAndConditions")) {
            return "redirect:/?success=false&error=3";
        }
        userInformationService.saveUserInformation(userInformationDTO);
        return "redirect:/?success=true";

    }

}

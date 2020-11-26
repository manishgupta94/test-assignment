package com.test.assignment;

import com.test.assignment.user.application.dto.UserInformationDTO;
import com.test.assignment.user.application.service.UserInformationService;
import com.test.assignment.user.domain.model.User;
import com.test.assignment.user.domain.repository.UserRepository;
import com.test.assignment.user.domain.repository.WorkSectorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Test class to test the service methods in the UserInformationService.java class
 *
 *
 * @author Manish Gupta
 * @version $Id: UserInformationServiceTest.java 1.0
 * @since 2020-11-11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AssignmentApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
public class UserInformationServiceTest {

    @Autowired
    UserInformationService userInformationService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WorkSectorRepository workSectorRepository;

    /**
     * Fetching user information of the logged in user before filling up any
     * form details and verifying the values are null/defaults.
     *
     */
    @Test
    public void givenNewUserInformationDTOFetchedIsEmpty() {
        UserInformationDTO userInformationDTO = userInformationService.fetchUserInformation("guest");
        assertNull(userInformationDTO.getName());
        assertEquals(0, userInformationDTO.getSelectedSectors().size());
        assertEquals(false, userInformationDTO.getAgreeToTermsAndConditions());
    }

    /**
     * Creating a user information form data and calling the service method
     * to save the data to the database and verifying that the form data is stored in the database.
     *
     */
    @Test
    public void givenUserSubmitsFormInformationDataIsStoredInDatabase() {
        //creating form data
        UserInformationDTO userInformationDTO = new UserInformationDTO();
        userInformationDTO.setUsername("guest");
        userInformationDTO.setName("John");
        userInformationDTO.setAgreeToTermsAndConditions(true);
        userInformationDTO.setSelectedSectors(new HashSet<>(Arrays.asList(1L, 2L, 3L)));

        //calling the saveUserInformation method to save the form data;
        userInformationService.saveUserInformation(userInformationDTO);

        //check the userRepository to verify the data.
        User user = userInformationService.getUserByUsername("guest");
        assertEquals("guest", user.getUsername());
        assertEquals("John", user.getName());
        assertEquals(true, user.getAgreeToTermsAndConditions());
    }

    /**
     * Creating a user information form data and calling the service method
     * to save the data and then fetching the user information via service method
     * and verifying that the fetched information is correct.
     *
     */
    @Test
    public void givenUserSubmitsFormInformationFormIsFilledWithData () {
        //creating form data
        UserInformationDTO userInformationDTO = new UserInformationDTO();
        userInformationDTO.setUsername("guest");
        userInformationDTO.setName("John");
        userInformationDTO.setAgreeToTermsAndConditions(true);
        userInformationDTO.setSelectedSectors(new HashSet<>(Arrays.asList(1L, 2L, 3L)));

        //calling the saveUserInformation method to save the form data;
        userInformationService.saveUserInformation(userInformationDTO);

        //fetch the UserInformationDTO using userInformationService
        UserInformationDTO filledUserInformationDTO = userInformationService.fetchUserInformation("guest");
        assertEquals("John", filledUserInformationDTO.getName());
        assertEquals(3, filledUserInformationDTO.getSelectedSectors().size());
        assertEquals(true, filledUserInformationDTO.getAgreeToTermsAndConditions());
    }



}

package com.test.assignment.user.application.service;

import com.test.assignment.user.application.dto.UserInformationDTO;
import com.test.assignment.user.application.exception.WorkSectorNotFoundException;
import com.test.assignment.user.domain.model.User;
import com.test.assignment.user.domain.model.WorkSector;
import com.test.assignment.user.domain.repository.UserRepository;
import com.test.assignment.user.domain.repository.WorkSectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service class providing methods to UserInformationController class that
 * fetch and updates data in the database using userRepository class and
 * workSectorRepository class.
 *
 *
 * @author Manish Gupta
 * @version $Id: UserInformationService.java 1.0
 * @since 2020-11-09
 */
@Service
public class UserInformationService {

    private final UserRepository userRepository;
    private final WorkSectorRepository workSectorRepository;

    @Autowired
    public UserInformationService(UserRepository userRepository, WorkSectorRepository workSectorRepository) {
        this.userRepository = userRepository;
        this.workSectorRepository = workSectorRepository;
    }

    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    public List<WorkSector> getRootWorkSectors() {
        return workSectorRepository.findByParentWorkSector(null);
    }

    /**
     * method that takes in User Filled Form Information and persists the User information
     * to the database after the user submits the form.
     *
     * @param userInformationDTO UserInformationDTO object containing User filled
     *                          form data that is to be saved to the database.
     */
//    @Transactional
    public void saveUserInformation(UserInformationDTO userInformationDTO) {
        User user = userRepository.getUserByUsername(userInformationDTO.getUsername());
        user.setName(userInformationDTO.getName());
        user.setAgreeToTermsAndConditions(userInformationDTO.getAgreeToTermsAndConditions());
        updateUserWorkSectors(user, userInformationDTO);
        userRepository.save(user);
    }

    /**
     * method that takes in username and fetches the User object by its username from the
     * database and sends it back to the controller.
     *
     * @param username username of the current User
     * @return a userInformationDTO object that contains the form information to be used by the view component.
     */
//    @Transactional
    public UserInformationDTO fetchUserInformation(String username) {
        User user = userRepository.getUserByUsername(username);
        UserInformationDTO userInformationDTO = new UserInformationDTO();
        userInformationDTO.setUsername(user.getUsername());
        userInformationDTO.setName(user.getName());
        userInformationDTO.setAgreeToTermsAndConditions(user.getAgreeToTermsAndConditions());
        Set<Long> listOfUserWorkSectorIds = user.getWorkSectors().stream().map(WorkSector::getId)
                .collect(Collectors.toSet());
        userInformationDTO.setSelectedSectors(listOfUserWorkSectorIds);
        return userInformationDTO;
    }

    /**
     * private helper method used by saveUserInformation method that takes in user and userInformationDTO
     * objects and updates the WorkSector the current user is involved in by adding or removing workSectors
     * or both, based on the difference between the existing workSector objects and the new workSector
     * objects submitted by the user.
     *
     * @param user User object for which the workSectors is to be updated.
     * @param userInformationDTO UserInformationDTO object containing User filled
     *                           form data with new sectors that is to be saved to the database.
     */
    private void updateUserWorkSectors(User user, UserInformationDTO userInformationDTO) {
        Set<WorkSector> workSectorsToBeRemovedFromTheUserAfterEdit = user.getWorkSectors().stream()
                .filter(workSector -> !(userInformationDTO.getSelectedSectors().contains(workSector.getId())))
                .collect(Collectors.toSet());
        for (WorkSector workSector : workSectorsToBeRemovedFromTheUserAfterEdit) {
            user.removeWorkSectorFromUser(workSector);
        }

        Set<WorkSector> newWorkSectorsAddedToTheUser = userInformationDTO.getSelectedSectors().stream()
                .map(id -> workSectorRepository.findById(id).orElseThrow(() -> new WorkSectorNotFoundException(id)))
                .filter(workSector -> !(user.getWorkSectors().contains(workSector)))
                .collect(Collectors.toSet());
        for(WorkSector workSector : newWorkSectorsAddedToTheUser) {
            user.addWorkSectorToUser(workSector);
        }
    }
}

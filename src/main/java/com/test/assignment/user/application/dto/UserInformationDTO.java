package com.test.assignment.user.application.dto;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

/**
 * DTO class mapping to the form data submitted by the user using index.html
 *
 * @author Manish Gupta
 * @version $Id: UserInformationDTO.java 1.0
 * @since 2020-11-10
 */
public class UserInformationDTO {

    private String username;

    @NotBlank
    @Size(min = 2, max = 35)
    private String name;

    @NotNull
    @NotEmpty
    private Set<Long> selectedSectors = new HashSet<>();

    @NotNull
    @AssertTrue
    private Boolean agreeToTermsAndConditions;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Long> getSelectedSectors() {
        return selectedSectors;
    }

    public void setSelectedSectors(Set<Long> selectedSectors) {
        this.selectedSectors = selectedSectors;
    }

    public Boolean getAgreeToTermsAndConditions() {
        return agreeToTermsAndConditions;
    }

    public void setAgreeToTermsAndConditions(Boolean agreeToTermsAndConditions) {
        this.agreeToTermsAndConditions = agreeToTermsAndConditions;
    }

    @Override
    public String toString() {
        return "UserInformationDTO{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", selectedSectors=" + selectedSectors +
                ", agreeToTermsAndConditions=" + agreeToTermsAndConditions +
                '}';
    }
}

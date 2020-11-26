package com.test.assignment.user.domain.model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Entity class for User which stores user information including username and password
 * and also its relation with WorkSector Entity.
 *
 * @author Manish Gupta
 * @version $Id: User.java 1.0
 * @since 2020-11-06
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    @Column(unique = true)
    private String username;
    private String password;
    private boolean agreeToTermsAndConditions = false;

    @ManyToMany
    @JoinTable(
            name = "user_work_sector",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "work_sector_id")
    )
    private Set<WorkSector> workSectors = new LinkedHashSet<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public Set<WorkSector> getWorkSectors() {
        return workSectors;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAgreeToTermsAndConditions(Boolean agreeToTermsAndConditions) {
        this.agreeToTermsAndConditions = agreeToTermsAndConditions;
    }

    public Boolean getAgreeToTermsAndConditions() {
        return agreeToTermsAndConditions;
    }

    /**
     * utility method to add the passed workSector to the current user.
     *
     * @param workSector WorkSector object selected by the user to be added to the current user.
     */

    public void addWorkSectorToUser(WorkSector workSector) {
        this.workSectors.add(workSector);
    }

    /**
     * utility method to remove the passed workSector from the current user.
     *
     * @param workSector WorkSector object to be removed from the current user.
     */

    public void removeWorkSectorFromUser(WorkSector workSector) {
        this.workSectors.remove(workSector);
    }

}

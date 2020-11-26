package com.test.assignment.user.application.exception;


/**
 * Exception class extending RuntimeException to be raised when WorkSector by given id does not exist.
 *
 * @author Manish Gupta
 * @version $Id: WorkSectorNotFoundException.java 1.0
 * @since 2020-11-10
 */
public class WorkSectorNotFoundException extends RuntimeException {

    public WorkSectorNotFoundException(Long sectorId) {
        super("WorkSector with id: " + sectorId + " not found");
    }

}

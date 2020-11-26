package com.test.assignment.user.domain.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class for WorkSector which stores sectors and its parent child hierarchy between multiple sectors.
 *
 * @author Manish Gupta
 * @version $Id: WorkSector.java 1.0
 * @since 2020-11-09
 */
@Entity
public class WorkSector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;

    @ManyToOne
    private WorkSector parentWorkSector;

    @OneToMany(mappedBy = "parentWorkSector", cascade = CascadeType.ALL)
    private List<WorkSector> childWorkSectors = new ArrayList<>();

    public WorkSector(String name) {
        this.name = name;
    }

    public WorkSector() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public WorkSector getParentWorkSector() {
        return parentWorkSector;
    }

    public List<WorkSector> getChildWorkSectors() {
        return childWorkSectors;
    }

    public void setParentWorkSector(WorkSector parentWorkSector) {
        this.parentWorkSector = parentWorkSector;
    }

    /**
     * utility method that adds a new WorkSector object using the name and adds the new workSector
     * as the child of current workSector and also sets the parent of newly created workSector
     * to the current workSector.
     *
     * @param workSectorName Name of the new WorkSector object to be added as the child of the current WorkSector
     */
    public WorkSector addChildWorkSectors(String workSectorName) {
        WorkSector child = new WorkSector(workSectorName);
        child.setParentWorkSector(this);
        this.childWorkSectors.add(child);
        return child;
    }



}

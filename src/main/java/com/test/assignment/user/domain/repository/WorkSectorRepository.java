package com.test.assignment.user.domain.repository;

import com.test.assignment.user.domain.model.WorkSector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository class for WorkSector Entity class
 *
 * @author Manish Gupta
 * @version $Id: WorkSectorRepository.java 1.0
 * @since 2020-11-09
 */
@Repository
public interface WorkSectorRepository extends JpaRepository<WorkSector, Long> {

    List<WorkSector> findByParentWorkSector(WorkSector parentWorkSector);

}

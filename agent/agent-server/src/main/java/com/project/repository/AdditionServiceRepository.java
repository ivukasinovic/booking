package com.project.repository;

import com.project.model.AdditionalService;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ivan V. on 18-Jun-18
 */
public interface AdditionServiceRepository extends JpaRepository<AdditionalService, Long> {


}

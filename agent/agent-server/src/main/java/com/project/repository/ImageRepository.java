package com.project.repository;

import com.project.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Dejan Stojkic (Smek) on 6/23/2018.
 */
public interface ImageRepository extends JpaRepository<Image,Long> {


}

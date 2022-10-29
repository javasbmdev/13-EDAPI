package com.mp.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mp.entities.CorrespondenceEntity;
@Repository
public interface CoRepository extends JpaRepository<CorrespondenceEntity, Serializable>{

}

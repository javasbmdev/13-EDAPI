package com.mp.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mp.entities.EdEntity;
@Repository
public interface EdRepository extends JpaRepository<EdEntity, Serializable>{

}

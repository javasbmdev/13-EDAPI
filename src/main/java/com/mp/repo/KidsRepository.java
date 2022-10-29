package com.mp.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mp.entities.KidsEntity;
@Repository
public interface KidsRepository extends JpaRepository<KidsEntity, Serializable> {
	public  List<KidsEntity> findByCaseNo(Long caseNo);
}

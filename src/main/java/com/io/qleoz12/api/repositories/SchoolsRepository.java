package com.io.qleoz12.api.repositories;

import com.io.qleoz12.api.entities.Schools;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolsRepository extends JpaRepository<Schools,Long> {


}

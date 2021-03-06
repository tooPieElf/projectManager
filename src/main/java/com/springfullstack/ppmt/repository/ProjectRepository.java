package com.springfullstack.ppmt.repository;

import com.springfullstack.ppmt.domain.Project;
import java.sql.PreparedStatement;
import java.util.List;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {


  Project findByProjectIdentifier(String projectId);
// the custom methods MUST have a "find" before it.
  @Override
  List<Project> findAll();
}

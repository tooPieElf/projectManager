package com.springfullstack.ppmt.repository;

import com.springfullstack.ppmt.domain.ProjectTask;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectTaskRepository extends JpaRepository<ProjectTask, Long> {

  List<ProjectTask> findByProjectIdentifierOrderByPriority(String id);

  ProjectTask findByProjectSequence(String sequence);

}

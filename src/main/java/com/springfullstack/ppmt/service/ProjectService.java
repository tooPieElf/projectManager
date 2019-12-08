package com.springfullstack.ppmt.service;

import com.springfullstack.ppmt.domain.Project;
import com.springfullstack.ppmt.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
  @Autowired
  private ProjectRepository projectRepository;

  public Project saveOrUpdateProject(Project project){
    // logic etc
    return projectRepository.save(project);
  }
}

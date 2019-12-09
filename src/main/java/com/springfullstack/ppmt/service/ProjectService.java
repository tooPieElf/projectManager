package com.springfullstack.ppmt.service;

import com.springfullstack.ppmt.domain.Project;
import com.springfullstack.ppmt.exceptions.ProjectIdExceptions;
import com.springfullstack.ppmt.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
  @Autowired
  private ProjectRepository projectRepository;

  public Project saveOrUpdateProject(Project project){

  try {
    project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase() );
    return projectRepository.save(project);
  } catch (Exception e){
    throw new ProjectIdExceptions("project ID " + project.getProjectIdentifier().toUpperCase()+" already exists" );
  }


  }
}

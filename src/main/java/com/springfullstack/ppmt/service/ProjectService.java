package com.springfullstack.ppmt.service;

import com.springfullstack.ppmt.domain.BackLog;
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
      if(project.getId()==null){
        BackLog backLog = new BackLog();
        project.setBackLog(backLog);
        backLog.setProject(project);
        backLog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
      }
    return projectRepository.save(project);
  } catch (Exception e){
    throw new ProjectIdExceptions("project ID " + project.getProjectIdentifier().toUpperCase()+" already exists" );
  }


  }

  public Project findProjectByIdentifier(String projectId){
      Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
      if(project ==null){
        throw new ProjectIdExceptions("project ID "+ projectId + " doesn't exists" );
      }
      return project;
  }
  public Iterable<Project> findAllProject(){
    return projectRepository.findAll();
  }

  public void deleteProjectsByIdentifier(String projectId){
    Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
    if(project==null){
      throw new ProjectIdExceptions("cannot Delete project with ID "+projectId+ " this project doesnt exist");
    }
    projectRepository.delete(project);
  }

}

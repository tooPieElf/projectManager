package com.springfullstack.ppmt.service;

import com.springfullstack.ppmt.domain.BackLog;
import com.springfullstack.ppmt.domain.ProjectTask;
import com.springfullstack.ppmt.repository.BacklogRepository;
import com.springfullstack.ppmt.repository.ProjectTaskRepository;
import org.hibernate.criterion.ProjectionList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {
  @Autowired
  private BacklogRepository backlogRepository;
  private ProjectTaskRepository projectTaskRepository;

  public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){

    BackLog backLog = backlogRepository.findByProjectIdentifier(projectIdentifier);
    projectTask.setBackLog(backLog);
    Integer BacklogSequence = backLog.getPTSequence();
    BacklogSequence++;


    projectTask.setProjectSequence(projectIdentifier+"-"+BacklogSequence);
    projectTask.setProjectIdentifier(projectIdentifier);

//    if(projectTask.getPriority()==0|| projectTask.getPriority()==null){
//      projectTask.setPriority(3);
//    }
    if(projectTask.getStatus()==""|| projectTask.getStatus()==null){
       projectTask.setStatus("TO_DO");
    }
    return projectTaskRepository.save(projectTask);
  }

}

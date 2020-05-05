package com.springfullstack.ppmt.service;

import com.springfullstack.ppmt.domain.BackLog;
import com.springfullstack.ppmt.domain.Project;
import com.springfullstack.ppmt.domain.ProjectTask;
import com.springfullstack.ppmt.exceptions.ProjectNotFoundException;
import com.springfullstack.ppmt.repository.BacklogRepository;
import com.springfullstack.ppmt.repository.ProjectRepository;
import com.springfullstack.ppmt.repository.ProjectTaskRepository;
import java.util.List;
import org.hibernate.criterion.ProjectionList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {
  @Autowired
  private BacklogRepository backlogRepository;
  @Autowired
  private ProjectTaskRepository projectTaskRepository;
  @Autowired
  private ProjectRepository projectRepository;

  public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){
  try{
    BackLog backLog = backlogRepository.findByProjectIdentifier(projectIdentifier);
    projectTask.setBackLog(backLog);
    Integer BacklogSequence = backLog.getPTSequence();
    BacklogSequence++;

    backLog.setPTSequence(BacklogSequence);


    projectTask.setProjectSequence(backLog.getProjectIdentifier()+"-"+BacklogSequence);
    projectTask.setProjectIdentifier(projectIdentifier);

    if(projectTask.getPriority()==null || projectTask.getPriority()==0){
      projectTask.setPriority(3);
    }
    if(projectTask.getStatus()=="" || projectTask.getStatus()==null){
      projectTask.setStatus("TO_DO");
    }
    return projectTaskRepository.save(projectTask);
  }catch (Exception e){
    throw new ProjectNotFoundException("project not found");
  }

  }

 public Iterable<ProjectTask>findBackLogById(String id){
    Project project = projectRepository.findByProjectIdentifier(id);

    if(project==null){
      throw new ProjectNotFoundException("project with ID " +id+ " does not exist");
    }

    return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
}

  public ProjectTask findPTByProjectSequence(String backlog_id, String pt_id){

    //make sure we are searching on an existing backlog
    BackLog backlog = backlogRepository.findByProjectIdentifier(backlog_id);
    if(backlog==null){
      throw new ProjectNotFoundException("Project with ID: '"+backlog_id+"' does not exist");
    }

    //make sure that our task exists
    ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);

    if(projectTask == null){
      throw new ProjectNotFoundException("Project Task '"+pt_id+"' not found");
    }

    //make sure that the backlog/project id in the path corresponds to the right project
    if(!projectTask.getProjectIdentifier().equals(backlog_id)){
      throw new ProjectNotFoundException("Project Task '"+pt_id+"' does not exist in project: '"+backlog_id);
    }


    return projectTask;
  }

  public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlog_id, String pt_id){
    ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id);

    projectTask = updatedTask;

    return projectTaskRepository.save(projectTask);
  }


  public void deletePTByProjectSequence(String backlog_id, String pt_id){
    ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id);
    System.out.println(projectTask);
    projectTaskRepository.delete(projectTask);
  }

}

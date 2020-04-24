package com.springfullstack.ppmt.controller;

import com.springfullstack.ppmt.domain.ProjectTask;
import com.springfullstack.ppmt.service.MapValidationErrorService;
import com.springfullstack.ppmt.service.ProjectTaskService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/backlog")
@CrossOrigin
public class BackLogController {

  @Autowired
  private ProjectTaskService projectTaskService;
  @Autowired
  private MapValidationErrorService mapValidationErrorService;

  @PostMapping("/{backlog_id}")
  public ResponseEntity<?> addPTtobacklog(@Valid @RequestBody ProjectTask projectTask,
                                           BindingResult result, @PathVariable String backlog_id){

    ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
    if(errorMap != null){
      return errorMap;
    }
    ProjectTask projectTask1 = projectTaskService.addProjectTask(backlog_id, projectTask);
    return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);
  }


}

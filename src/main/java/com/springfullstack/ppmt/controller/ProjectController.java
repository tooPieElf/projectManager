package com.springfullstack.ppmt.controller;

import com.springfullstack.ppmt.domain.Project;
import com.springfullstack.ppmt.service.MapValidationErrorService;
import com.springfullstack.ppmt.service.ProjectService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
public class ProjectController<result> {

  @Autowired
  private ProjectService projectService;


  @Autowired
  private MapValidationErrorService mapValidationErrorService;
  @PostMapping("")
  public ResponseEntity<?> createNewProject (@Valid @RequestBody Project project, BindingResult result){
    ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
    if(errorMap!=null) return errorMap;
    Project project1 = projectService.saveOrUpdateProject(project);
    return new ResponseEntity<Project>(project, HttpStatus.CREATED);
  }

}
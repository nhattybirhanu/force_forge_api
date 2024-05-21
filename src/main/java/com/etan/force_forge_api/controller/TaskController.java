package com.etan.force_forge_api.controller;

import com.etan.force_forge_api.CreatTask;
import com.etan.force_forge_api.Service.TaskService;
import com.etan.force_forge_api.dto.UpdateTaskField;
import com.etan.force_forge_api.dto.UpdateTaskRequest;
import com.etan.force_forge_api.model.SubTask;
import com.etan.force_forge_api.model.Tag;
import com.etan.force_forge_api.model.Task;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")
@Slf4j
public class TaskController {
    @Autowired
    private TaskService taskService;


    @PostMapping("/add")
    public Task addTask(@RequestBody @Valid CreatTask creatTask){
        return taskService.newTask(creatTask);
    }
    @PutMapping("/update")
    public Task update(@RequestBody CreatTask updateTaskRequest){
        return taskService.updateTask(updateTaskRequest);
    }

    @DeleteMapping("/{id}/tag/{tagId}")
    public Task removeTag(@PathVariable("id") String id,@PathVariable("tagId")  String tagId){
        return taskService.removeTag(id, tagId);
    }
    @PutMapping("/{id}/tag/{tagId}")
    public Task addTag(@PathVariable("id") String id,@PathVariable("tagId")  String tagName){
        return taskService.addTag(id, new Tag(tagName));
    }
    @DeleteMapping("/{id}/subtask/{subtaskid}")
    public Task removeSubTask(@PathVariable("id") String id,@PathVariable("subtaskid")  String subTaskId){
        return taskService.removeSubTask(id, subTaskId);
    }
    @PutMapping("/{id}/subtask")
    public Task updateSubTask(@PathVariable("id") String id,@RequestBody SubTask subTask){
        return taskService.updateSubTask(id, subTask);
    }
    @PutMapping("/{id}/subtask/")
    public Task addSubTask(@PathVariable("id") String id,@RequestBody SubTask subTask){
        return taskService.addSubStask(id, subTask);
    }
    @GetMapping("/")
    public List<Task> tasks(){
        return taskService.tasks();
    }
    @GetMapping("/{id}")
    public Task tasks(@PathVariable("id") String id){
        return taskService.findById(id);
    }



}

package com.etan.force_forge_api.controller;

import com.etan.force_forge_api.CreatTask;
import com.etan.force_forge_api.Service.TaskService;
import com.etan.force_forge_api.dto.UpdateTaskField;
import com.etan.force_forge_api.dto.UpdateTaskRequest;
import com.etan.force_forge_api.model.SubTask;
import com.etan.force_forge_api.model.Tag;
import com.etan.force_forge_api.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;


    @PostMapping("/add")
    public Task addTask(@RequestBody  CreatTask creatTask){
        return taskService.newTask(creatTask);
    }
    @PutMapping("/update")
    public Task update(@RequestBody UpdateTaskRequest updateTaskRequest){
        return taskService.updateTask(updateTaskRequest);
    }

    @DeleteMapping("/{id}/tag/{tagName}")
    public Task removeTag(@PathVariable("id") String id,@PathVariable("tagName")  String tagName){
        return taskService.removeTag(id, new Tag(tagName));
    }
    @PutMapping("/{id}/tag/{tagName}")
    public Task addTag(@PathVariable("id") String id,@PathVariable("tagName")  String tagName){
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

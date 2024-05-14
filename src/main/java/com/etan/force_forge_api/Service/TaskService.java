package com.etan.force_forge_api.Service;

import com.etan.force_forge_api.CreatTask;
import com.etan.force_forge_api.dto.UpdateTaskField;
import com.etan.force_forge_api.dto.UpdateTaskRequest;
import com.etan.force_forge_api.model.*;
import com.etan.force_forge_api.repositories.TaskRepositories;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {
    final private TaskRepositories taskRepositories;

    public TaskService(TaskRepositories taskRepositories) {
        this.taskRepositories = taskRepositories;
    }



    public Task newTask(CreatTask creatTask){

        Task task=new Task();
        task.setTitle(creatTask.getTitle());
        task.setDescription(creatTask.getDescription());
        task.setDuration(creatTask.getDuration());
        task.setTaskStartDate(creatTask.getTaskStartDate());
        task.setRepetition(creatTask.getRepetition());
        taskRepositories.save(task);
        return  task;
    }

    public Task  updateTask(UpdateTaskRequest updateTaskRequest){
        Optional<Task> optionalTask=taskRepositories.findById(updateTaskRequest.getTaskId()).stream().filter(task -> task.getId().equals(updateTaskRequest.getTaskId())).findFirst();
            Task task=null;
            if (optionalTask.isPresent()){
                Field field;
                task=optionalTask.get();

                for (UpdateTaskField updateTaskField : updateTaskRequest.getUpdateTaskFields()) {
                    try {

                        field = task.getClass().getDeclaredField(updateTaskField.getFieldName());
                        field.setAccessible(true);
                        String fieldType= updateTaskField.getFieldType();
                        String value= updateTaskField.getValue();
                        if(fieldType.equals("string"))


                            field.set(task, updateTaskField.getValue());
                        else if (fieldType.equals("enum")) {
                            Enum anEnum=Enum.valueOf(enumClass(updateTaskField.getFieldName()),value);
                            field.set(task, anEnum);

                        }
                        else if (fieldType.equals("boolean")){
                            field.setBoolean(task, Boolean.parseBoolean(value));
                        } else if (fieldType.equals("int") || fieldType.equals("long"))
                            field.setLong(task, Long.parseLong(value));
                        else if (fieldType.equals("date")){
                            LocalDate localDate=LocalDate.parse(value);
                            field.set(task, localDate);

                        } else if (fieldType.equals("dateTime")){
                            LocalDateTime localDateTime=LocalDateTime.parse(value);
                            field.set(task, localDateTime);

                        }

                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        System.out.println(task.getId());
        return task;
    }
    Class enumClass(String field){
        if (field.equals("repetition")) return Repetition.class;
    else
        if (field.equals("priority")) return Priority.class;


        return Class.class;
    }

    public Task addSubStask(String taskId,SubTask subTask){
        Task task=taskRepositories.findById(taskId).stream().filter(task1 -> task1.getId().equals(taskId)).findFirst().orElseThrow();
        task.addSubTask(subTask);
        return task;
    }
    public Task updateSubTask(String taskId,SubTask subTask){
        Task task=taskRepositories.findById(taskId).stream().filter(task1 -> task1.getId().equals(taskId)).findFirst().orElseThrow();
        task.updateSubTask(subTask);
        return task;
    }
    public Task addTag(String taskId,Tag tag){
        Task task=taskRepositories.findById(taskId).stream().filter(task1 -> task1.getId().equals(taskId)).findFirst().orElseThrow();
        task.addTag(tag);
        return task;
    }
    public Task removeTag(String taskId, Tag tag){
        Task task=taskRepositories.findById(taskId).stream().filter(task1 -> task1.getId().equals(taskId)).findFirst().orElseThrow();
        task.removeTag(tag);
        return task;
    }

    public Task removeSubTask(String taskId, String subTaskId){
        Task task=taskRepositories.findById(taskId).stream().filter(task1 -> task1.getId().equals(taskId)).findFirst().orElseThrow();
        task.removeSubTask(subTaskId);
        return task;
    }
    public List<Task> tasks() { return taskRepositories.findAll();}
    public Task findById(String id) { return taskRepositories.findById(id).orElseThrow();}
}

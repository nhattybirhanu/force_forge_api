package com.etan.force_forge_api.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Document("tasks")
public class Task {
    @MongoId
    String id;
    String userId;
    public String title;
    String description;
    long duration;
    LocalDateTime startTime;
    LocalDateTime endTime;
    public long repetitionCount;
    Repetition repetition;
    LocalDate taskStartDate;
    LocalDate taskEndDate;
    boolean onAlarm;
    List<SubTask> subTasks;
    Priority priority;
    Category category;
    List<Tag> tags;

    public void addSubTask(SubTask subTask){
        if (subTasks==null) subTasks=new ArrayList<>();
        subTask.id= UUID.randomUUID().toString();
        subTasks.add(subTask);
    }
    public void addTag(Tag tag){
        if (tags==null) tags=new ArrayList<>();
        tags.add(tag);
    }
    public void removeTag(Tag tag){
        if (tags==null) tags=new ArrayList<>();
        tags.remove(tag);
    }
    public void updateSubTask(SubTask subTask){
        if (subTasks!=null){
            subTasks.stream().filter(subTask1 -> subTask1.id.equals(subTask.id)).map(subTask1 ->
                    subTask
                    );


        }
    }
    public void removeSubTask(String id){
        if (subTasks!=null){
            subTasks.stream().filter(subTask1 -> subTask1.id.equals(id)).findFirst().ifPresent(subTask -> {
                subTasks.remove(subTask);
                           });


        }
    }

}

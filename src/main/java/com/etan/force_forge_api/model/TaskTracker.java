package com.etan.force_forge_api.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class TaskTracker {

    String taskId;


    List<TaskReport> taskReports;

    public TaskTracker(String taskId, List<TaskReport> taskReports) {
        this.taskId = taskId;
        this.taskReports = taskReports;
    }
}

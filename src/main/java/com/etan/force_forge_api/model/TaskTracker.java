package com.etan.force_forge_api.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
@Document("taskTracker")

public class TaskTracker {

    String taskId;
    List<TaskReport> taskReports;
    @CreatedDate
    LocalDateTime createdDate;
}

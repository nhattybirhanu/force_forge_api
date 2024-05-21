package com.etan.force_forge_api.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Document("taskReport")
@Data

public class TaskReport {
    String id;

    String taskId;

    boolean isDone;
    DailyNote dailyNote;
    @CreatedDate
    LocalDateTime createdDate;
    LocalDate date;
    LocalTime startTime;
    LocalTime endTime;

}

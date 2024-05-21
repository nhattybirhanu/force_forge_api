package com.etan.force_forge_api.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Valid
public class TaskReportRequest {
    @NotNull(message = "Task id can not be null")
    @NotEmpty(message = "Task id can not be Empty")
    String taskId;

    @NotNull(message = "Date  can not be null")
    LocalDate date;

    @NotNull(message = "Start Time can not be null")
    LocalTime startTime;

    @NotNull(message = "End Time can not be null")
    LocalTime endTime;

    DailyNote dailyNote;
}

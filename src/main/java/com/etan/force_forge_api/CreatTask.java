package com.etan.force_forge_api;

import com.etan.force_forge_api.model.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@Valid
public class CreatTask {
    String taskId;
    @NotNull(message = "Title can not be empty")
    @NotEmpty(message = "Title can not be empty")
    String title;
    int repetitionCount;
    String description;
    long duration;

    @NotNull(message = "Repetition should be selected")
    Repetition repetition;
    @NotNull(message = "Days should can not be empty")
    List<Day>  days;

    @NotNull(message = "Priority should be selected")
    Priority priority;

    @NotNull(message = "Category should be selected")
    Category category;

    List<SubTask> subTasks;

    List<Tag> selectedTag;

    @NotNull(message = "Start Date can not ne null")
    LocalDate taskStartDate;

    LocalDate taskEndDate;

    @NotNull(message = "Start Time can not ne null")
    LocalTime startTime;

    @NotNull(message = "End Time can not ne null")
    LocalTime endTime;

}

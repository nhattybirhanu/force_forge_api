package com.etan.force_forge_api;

import com.etan.force_forge_api.model.Repetition;
import jakarta.validation.Valid;
import lombok.Data;

import java.time.LocalDate;

@Data
@Valid
public class CreatTask {
    String title;
    int repetitionCount;
    String description;
    long duration;
    Repetition repetition;
    LocalDate taskStartDate;



}

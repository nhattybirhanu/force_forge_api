package com.etan.force_forge_api.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class UpdateTaskRequest {
    String taskId;
    List<UpdateTaskField> updateTaskFields=new ArrayList<>();
}

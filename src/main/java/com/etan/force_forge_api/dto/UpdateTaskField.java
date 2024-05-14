package com.etan.force_forge_api.dto;

import lombok.Data;

import java.util.List;
@Data
public class UpdateTaskField {
    String fieldName;
    String value;

    String fieldType;

    List<Object> list;

}

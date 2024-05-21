package com.etan.force_forge_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubTask {
    String id;
    String description;
    TaskStatus taskStatus;


    public SubTask(String id, String description) {
        this.id = id;
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubTask subTask)) return false;
        return Objects.equals(id, subTask.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

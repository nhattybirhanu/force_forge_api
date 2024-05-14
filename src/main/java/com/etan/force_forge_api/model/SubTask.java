package com.etan.force_forge_api.model;

import java.util.Objects;

public class SubTask {
    String id;
    String description;
    boolean isDone;

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

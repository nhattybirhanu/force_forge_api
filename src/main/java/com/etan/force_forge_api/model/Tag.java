package com.etan.force_forge_api.model;

import lombok.Data;

import java.util.Objects;
@Data
public class Tag {
    String title;

    public Tag(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag tag)) return false;
        return Objects.equals(title, tag.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}

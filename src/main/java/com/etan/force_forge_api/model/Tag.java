package com.etan.force_forge_api.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
@Data
@Document("tags")
@NoArgsConstructor
public class Tag {
    String id;
    String title;
    String userId;
    boolean userTag;

    public Tag(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public Tag(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag tag)) return false;
        return Objects.equals(getId(), tag.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

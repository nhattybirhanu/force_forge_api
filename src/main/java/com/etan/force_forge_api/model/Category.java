package com.etan.force_forge_api.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document("categories ")
@Data
@Valid
@NoArgsConstructor
public class Category {
    String id;
    String userId;
    @NotNull
    String title;
    boolean sub=false;
    List<Category> subCategory;
    boolean userCategory=false;

    public Category(String id, @NotNull String title) {
        this.id = id;
        this.title = title;
    }
}

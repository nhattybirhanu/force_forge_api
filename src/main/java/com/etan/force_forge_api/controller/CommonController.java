package com.etan.force_forge_api.controller;

import com.etan.force_forge_api.model.Category;
import com.etan.force_forge_api.model.Priority;
import com.etan.force_forge_api.model.Repetition;
import com.etan.force_forge_api.model.Tag;
import com.etan.force_forge_api.repositories.CategoryRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("api/v1/common")
@RestController
public class CommonController {
    @Autowired
    CategoryRepositories categoryRepositories;
    @GetMapping("/tags")
    public List<Tag> tags(){
        return commonTags();
    }
    @GetMapping("/categories")
    public List<Category> categories(){
        List<Category> categories=categoryRepositories.findAll();
        categories.addAll(commonCategories());
        return categories;
    }
    @PostMapping("/categories")
    public Category addCategory(@RequestBody Category category){
        category.setUserCategory(true);
        return categoryRepositories.save(category);
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategory (@PathVariable("id") String catId){
        categoryRepositories.deleteById(catId);

    }
    @PutMapping("/categories/{id}/{title}")
    public void updateCategoryName(@PathVariable("id") String catId , @PathVariable("title") String title){
        categoryRepositories.findById(catId).ifPresent(category -> {
            category.setTitle(title);
            categoryRepositories.save(category);
        });
    }

    @GetMapping("/priorities")
    public List<Priority> priorities(){
        return Arrays.stream(Priority.values()).toList();
    }
    @GetMapping("/repetitions")
    public List<Repetition> repetitions(){
        return Arrays.stream(Repetition.values()).toList();
    }

    public List<Category> commonCategories(){

        return List.of(
                new Category("0001","Personal"),
                new Category("0002","Work"),
                new Category("0003","Shopping"),
                new Category("0004","Chore"),
                new Category("0005","Physical Exercise"),
                new Category("0006","Health"),
                new Category("0007","Learning"),
                new Category("0008","School"),
                new Category("0009","Entertainment"),
                new Category("0010","Travel")

        );
    }
    public List<Tag> commonTags(){

        return List.of(
                new Tag("Important"),
                new Tag("Dead Line"),
                new Tag("Family"),
                new Tag("Track back"),
                new Tag("Personal Project"),
                new Tag("Shows"),
                new Tag("Quiting"),
                new Tag("Learn"),
                new Tag("Top Priority")
        );
    }



}

package com.etan.force_forge_api.controller;

import com.etan.force_forge_api.model.Category;
import com.etan.force_forge_api.model.Priority;
import com.etan.force_forge_api.model.Repetition;
import com.etan.force_forge_api.model.Tag;
import com.etan.force_forge_api.repositories.CategoryRepositories;
import com.etan.force_forge_api.repositories.TagRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RequestMapping("api/v1/common")
@RestController
public class CommonController {
    @Autowired
    CategoryRepositories categoryRepositories;

    @Autowired
    TagRepositories tagRepositories;
    @GetMapping("/tags")
    public List<Tag> tags(){
        List<Tag> tags=tagRepositories.findAll();
        tags.addAll(commonTags());
        return tags;
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
    @PostMapping("/tags")
    public Tag tags(@RequestBody Tag tag){
        tag.setUserTag(true);
        return tagRepositories.save(tag);
    }
    @DeleteMapping("/tags/{id}")
    public void deleteTag (@PathVariable("id") String tagId){
        tagRepositories.deleteById(tagId);

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

    @PutMapping("/tags/{id}/{title}")
    public void updateTagName(@PathVariable("id") String tagId , @PathVariable("title") String title){
        tagRepositories.findById(tagId).ifPresent(tag -> {
            tag.setTitle(title);
            tagRepositories.save(tag);
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
                new Tag("0001","Important"),
                new Tag("0002","Dead Line"),
                new Tag("0003","Family"),
                new Tag("0004","Track back"),
                new Tag("0005","Personal Project"),
                new Tag("0006","Shows"),
                new Tag("0007","Quiting"),
                new Tag("0008","Learn"),
                new Tag("0009","Top Priority")
        );
    }



}

package com.cydeo.controller;


import com.cydeo.dto.CategoryDto;
import com.cydeo.dto.ResponseWrapper;
import com.cydeo.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.ws.rs.Path;
import java.util.List;

@RequestMapping("/api/v1/category")
@RestController
public class CategoryController {


    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> getAllCategories(){

       List<CategoryDto> categoryDtoList=categoryService.findAll();
       return ResponseEntity.ok(new ResponseWrapper("retrieved all categories",categoryDtoList, HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper> getByCategoryId(@PathVariable("id") Long id){
        CategoryDto categoryDto=categoryService.findById(id);
        return ResponseEntity.ok(new ResponseWrapper("category is retrieved",categoryDto,HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper> createCategory(@RequestBody CategoryDto categoryDto){
        categoryService.save(categoryDto);
        return ResponseEntity.ok(new ResponseWrapper("category is successfully created",HttpStatus.CREATED));

    }
    @PutMapping
    public ResponseEntity<ResponseWrapper> updateCategory(@RequestBody CategoryDto categoryDto){
        categoryService.update(categoryDto);
        return ResponseEntity.ok(new ResponseWrapper("category is successfully created",HttpStatus.CREATED));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper> deleteByCategoryId(@PathVariable("id") Long id){
        categoryService.delete(id);
        return ResponseEntity.ok(new ResponseWrapper("category is successfully deleted",HttpStatus.OK));
    }

}

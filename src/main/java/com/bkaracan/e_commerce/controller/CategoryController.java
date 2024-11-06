package com.bkaracan.e_commerce.controller;

import com.bkaracan.e_commerce.dto.CategoryDTO;
import com.bkaracan.e_commerce.dto.core.ResponsePayload;
import com.bkaracan.e_commerce.dto.request.CreateCategoryRequestDTO;
import com.bkaracan.e_commerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping
  public ResponsePayload<CategoryDTO> getCategoryById(@RequestParam Long categoryId) {
    return categoryService.findCategoryById(categoryId);
  }

  @PostMapping("/save")
  public ResponsePayload<CategoryDTO> createCategory(
      @RequestBody CreateCategoryRequestDTO createCategoryRequestDTO) {
    return categoryService.createCategory(createCategoryRequestDTO);
  }

  @PutMapping("/update")
  public ResponsePayload<CategoryDTO> updateCategory(
      @RequestParam Long categoryId,
      @RequestBody CreateCategoryRequestDTO createCategoryRequestDTO) {
      return categoryService.updateCategory(categoryId, createCategoryRequestDTO);
  }

  @GetMapping("/")
  public ResponsePayload<List<CategoryDTO>> getAllCategories() {
    return categoryService.getAllCategories();
  }

  @DeleteMapping("/deleteById")
  public ResponsePayload<Void> deleteCategoryById(@RequestParam Long categoryId) {
    return categoryService.deleteCategoryById(categoryId);
  }
}

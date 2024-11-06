package com.bkaracan.e_commerce.service;

import com.bkaracan.e_commerce.dto.CategoryDTO;
import com.bkaracan.e_commerce.dto.core.ResponsePayload;
import com.bkaracan.e_commerce.dto.request.CreateCategoryRequestDTO;

import java.util.List;

public interface CategoryService {

  ResponsePayload<CategoryDTO> findCategoryById(Long categoryId);

  ResponsePayload<CategoryDTO> createCategory(CreateCategoryRequestDTO createCategoryRequestDTO);

  ResponsePayload<CategoryDTO> updateCategory(Long categoryId, CreateCategoryRequestDTO createCategoryRequestDTO);

  ResponsePayload<List<CategoryDTO>> getAllCategories();

  ResponsePayload<Void> deleteCategoryById(Long categoryId);
}

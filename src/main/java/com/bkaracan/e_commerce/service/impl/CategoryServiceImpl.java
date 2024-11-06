package com.bkaracan.e_commerce.service.impl;

import com.bkaracan.e_commerce.bean.*;
import com.bkaracan.e_commerce.dto.CategoryDTO;
import com.bkaracan.e_commerce.dto.core.ResponsePayload;
import com.bkaracan.e_commerce.dto.request.CreateCategoryRequestDTO;
import com.bkaracan.e_commerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class CategoryServiceImpl implements CategoryService {

  private final FindCategoryBean findCategoryBean;
  private final SaveCategoryBean saveCategoryBean;
  private final UpdateCategoryBean updateCategoryBean;
  private final ListCategoryBean listCategoryBean;
  private final DeleteCategoryBean deleteCategoryBean;

  @Override
  public ResponsePayload<CategoryDTO> findCategoryById(Long categoryId) {
    return findCategoryBean.findCategoryById(categoryId);
  }

  @Override
  public ResponsePayload<CategoryDTO> createCategory(
      CreateCategoryRequestDTO createCategoryRequestDTO) {
    return saveCategoryBean.saveCategory(createCategoryRequestDTO);
  }

  @Override
  public ResponsePayload<CategoryDTO> updateCategory(Long categoryId, CreateCategoryRequestDTO createCategoryRequestDTO) {
    return updateCategoryBean.updateCategory(categoryId, createCategoryRequestDTO);
  }

  @Override
  public ResponsePayload<List<CategoryDTO>> getAllCategories() {
    return listCategoryBean.getAllCategories();
  }


  @Override
  public ResponsePayload<Void> deleteCategoryById(Long categoryId) {
    return deleteCategoryBean.deleteCategoryById(categoryId);
  }
}

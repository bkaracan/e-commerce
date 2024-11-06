package com.bkaracan.e_commerce.mapper;

import com.bkaracan.e_commerce.dto.CategoryDTO;
import com.bkaracan.e_commerce.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryDtoMapper {

  public CategoryDTO map(Category category) {
    return CategoryDTO.Builder.categoryWith().id(category.getId()).name(category.getName()).build();
  }

  public List<CategoryDTO> mapList(List<Category> list) {
    List<CategoryDTO> mappedList = new ArrayList<>();
    for (Category category : list) {
      mappedList.add(this.map(category));
    }
    return mappedList;
  }

  public CategoryDTO mapWithObjects(Category category) {
    return CategoryDTO.Builder.categoryWith().id(category.getId()).name(category.getName()).build();
  }

  public List<CategoryDTO> mapListWithObjects(List<Category> list) {
    List<CategoryDTO> mappedList = new ArrayList<>();
    for (Category category : list) {
      mappedList.add(this.mapWithObjects(category));
    }
    return mappedList;
  }

  public Category convertToEntity(CategoryDTO categoryDto) {
    return categoryDto == null
        ? null
        : CategoryDTO.Builder.categoryWith().convertToEntity(categoryDto);
  }

  public Page<CategoryDTO> mapPage(Page<Category> categoryPage) {
    return new PageImpl<>(
        mapList(categoryPage.getContent()),
        categoryPage.getPageable(),
        categoryPage.getTotalElements());
  }

  public Page<CategoryDTO> mapPageWithObjects(Page<Category> categoryPage) {
    return new PageImpl<>(
        mapListWithObjects(categoryPage.getContent()),
        categoryPage.getPageable(),
        categoryPage.getTotalElements());
  }
}

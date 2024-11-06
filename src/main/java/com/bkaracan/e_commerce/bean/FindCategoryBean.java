package com.bkaracan.e_commerce.bean;

import com.bkaracan.e_commerce.dto.core.AbstractResponsePayload;
import com.bkaracan.e_commerce.dto.CategoryDTO;
import com.bkaracan.e_commerce.dto.core.ResponsePayload;
import com.bkaracan.e_commerce.enumaration.MessageEnum;
import com.bkaracan.e_commerce.enumaration.ResponseEnum;
import com.bkaracan.e_commerce.mapper.CategoryDtoMapper;
import com.bkaracan.e_commerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class FindCategoryBean extends AbstractResponsePayload {

  private final CategoryRepository categoryRepository;
  private final CategoryDtoMapper categoryDtoMapper;

  public ResponsePayload<CategoryDTO> findCategoryById(Long categoryId) {
    Optional<CategoryDTO> categoryDto =
        categoryRepository.findById(categoryId).map(categoryDtoMapper::map);

    return categoryDto
        .map(dto -> setResponse(ResponseEnum.OK, MessageEnum.FETCH_SUCCESS, dto))
        .orElseGet(
            () ->
                setResponse(
                    ResponseEnum.NOT_FOUND, MessageEnum.NOT_FOUND.getMessage(), null, true));
  }
}

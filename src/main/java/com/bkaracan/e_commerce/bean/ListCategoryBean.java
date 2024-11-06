package com.bkaracan.e_commerce.bean;

import com.bkaracan.e_commerce.dto.CategoryDTO;
import com.bkaracan.e_commerce.dto.core.AbstractResponsePayload;
import com.bkaracan.e_commerce.dto.core.ResponsePayload;
import com.bkaracan.e_commerce.enumaration.MessageEnum;
import com.bkaracan.e_commerce.enumaration.ResponseEnum;
import com.bkaracan.e_commerce.mapper.CategoryDtoMapper;
import com.bkaracan.e_commerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class ListCategoryBean extends AbstractResponsePayload {

    private final CategoryRepository categoryRepository;
    private final CategoryDtoMapper categoryDtoMapper;

    public ResponsePayload<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categoryList = categoryRepository.findAll().stream()
                .map(categoryDtoMapper::map)
                .toList();

        // Kategoriler listesi boşsa uygun mesaj döner
        if (categoryList.isEmpty()) {
            return setResponse(ResponseEnum.OK, MessageEnum.EMPTY_LIST.getMessage(), categoryList, false);
        }

        // Kategoriler listesi doluysa başarı yanıtı ile dön
        return setResponse(ResponseEnum.OK, MessageEnum.FETCH_SUCCESS.getMessage(), categoryList, true);
    }
}

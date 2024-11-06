package com.bkaracan.e_commerce.bean;

import com.bkaracan.e_commerce.dto.core.AbstractResponsePayload;
import com.bkaracan.e_commerce.dto.core.ResponsePayload;
import com.bkaracan.e_commerce.enumaration.MessageEnum;
import com.bkaracan.e_commerce.enumaration.ResponseEnum;
import com.bkaracan.e_commerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class DeleteCategoryBean extends AbstractResponsePayload {

    private final CategoryRepository categoryRepository;

    @Transactional
    public ResponsePayload<Void> deleteCategoryById(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            // Kategori bulunamadı, NOT_FOUND yanıtı döndür
            return setResponse(ResponseEnum.NOT_FOUND, MessageEnum.NOT_FOUND.getMessage(), null, true);
        }

        // Kategori mevcut, silme işlemi gerçekleştir
        categoryRepository.deleteById(categoryId);

        // Başarı yanıtı döndür
        return setResponse(ResponseEnum.OK, MessageEnum.DELETE_SUCCESS.getMessage(), null, false);
    }
}

package com.bkaracan.e_commerce.bean;

import com.bkaracan.e_commerce.dto.CategoryDTO;
import com.bkaracan.e_commerce.dto.core.AbstractResponsePayload;
import com.bkaracan.e_commerce.dto.core.ResponsePayload;
import com.bkaracan.e_commerce.dto.request.CreateCategoryRequestDTO;
import com.bkaracan.e_commerce.enumaration.MessageEnum;
import com.bkaracan.e_commerce.enumaration.ResponseEnum;
import com.bkaracan.e_commerce.mapper.CategoryDtoMapper;
import com.bkaracan.e_commerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class UpdateCategoryBean extends AbstractResponsePayload {

  private final FindCategoryBean findCategoryBean;
  private final SaveCategoryBean saveCategoryBean;
  private final CategoryDtoMapper categoryDtoMapper;
  private final CategoryRepository categoryRepository;

  @Transactional
  public ResponsePayload<CategoryDTO> updateCategory(
      Long categoryId, CreateCategoryRequestDTO createCategoryRequestDTO) {
    // Veritabanında kategori mevcut mu kontrol et
    ResponsePayload<CategoryDTO> findResponse = findCategoryBean.findCategoryById(categoryId);
    if (Boolean.FALSE.equals(findResponse.getSuccess())) {
      // Kategori mevcut değil, NOT_FOUND yanıtı dön
      return setResponse(ResponseEnum.NOT_FOUND, MessageEnum.NOT_FOUND.getMessage(), null, true);
    }

    // Güncellenen kategori adını normalize et
    String normalizedCategoryName =
        saveCategoryBean.normalizeCategoryName(createCategoryRequestDTO.getCategoryName());

    // Aynı isimde başka bir kategori mevcut mu kontrol et
    boolean isDuplicateNameExists =
        categoryRepository.existsByNameAndIdNot(normalizedCategoryName, categoryId);
    if (isDuplicateNameExists) {
      return setResponse(ResponseEnum.ERROR, MessageEnum.RECORD_EXISTS.getMessage(), null, true);
    }

    // Güncellenmiş kategoriyle yeni bir kayıt oluştur
    CategoryDTO existingCategoryDTO = findResponse.getData();
    existingCategoryDTO.setName(normalizedCategoryName);

    // Kategoriyi kaydet
    return setResponse(
        ResponseEnum.OK,
        MessageEnum.UPDATE_SUCCESS,
        categoryDtoMapper.map(
            categoryRepository.save(categoryDtoMapper.convertToEntity(existingCategoryDTO))));
  }
}

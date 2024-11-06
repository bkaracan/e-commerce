package com.bkaracan.e_commerce.bean;

import com.bkaracan.e_commerce.dto.CategoryDTO;
import com.bkaracan.e_commerce.dto.core.AbstractResponsePayload;
import com.bkaracan.e_commerce.dto.core.ResponsePayload;
import com.bkaracan.e_commerce.dto.request.CreateCategoryRequestDTO;
import com.bkaracan.e_commerce.entity.Category;
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
public class SaveCategoryBean extends AbstractResponsePayload {

  private final CategoryRepository categoryRepository;
  private final CategoryDtoMapper categoryDtoMapper;

  @Transactional
  public ResponsePayload<CategoryDTO> saveCategory(
      CreateCategoryRequestDTO createCategoryRequestDTO) {

    // categoryName alanının boş olup olmadığını kontrol et
    if (createCategoryRequestDTO.getCategoryName() == null
        || createCategoryRequestDTO.getCategoryName().isBlank()) {
      return setResponse(ResponseEnum.BAD_REQUEST, MessageEnum.NULL_OR_BLANK.getMessage(), null, true);
    }

    // Kategori adını normalize et: ilk harfi büyük yap, geri kalanı küçük
    String normalizedCategoryName =
        normalizeCategoryName(createCategoryRequestDTO.getCategoryName());

    // Aynı isimde bir kategori mevcut mu kontrol et
    boolean isCategoryExists = categoryRepository.existsByName(normalizedCategoryName);

    if (isCategoryExists) {
      // Kategori mevcut, hata mesajı dön
      return setResponse(ResponseEnum.ERROR, MessageEnum.RECORD_EXISTS.getMessage(), null, true);
    }

    // Yeni kategori oluştur ve kaydet
    Category newCategory = new Category();
    newCategory.setName(normalizedCategoryName); // Düzenlenmiş adı kullan
    Category savedCategory = categoryRepository.save(newCategory);

    CategoryDTO savedCategoryDTO = categoryDtoMapper.map(savedCategory);

    // Başarı mesajı dön
    return setResponse(
        ResponseEnum.OK, MessageEnum.SAVE_SUCCESS.getMessage(), savedCategoryDTO, true);
  }

  // Kategori adını normalize eden yardımcı metot
  public String normalizeCategoryName(String categoryName) {
    return categoryName.substring(0, 1).toUpperCase() + categoryName.substring(1).toLowerCase();
  }
}

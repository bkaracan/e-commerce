package com.bkaracan.e_commerce.dto;

import com.bkaracan.e_commerce.entity.Category;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CategoryDTO implements Serializable {
    private Long id;
    private String name;

    public CategoryDTO() {
    }

    private CategoryDTO(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public static class Builder {
        private Long id;
        private String name;

        private Builder() {
        }

        public static Builder categoryWith() {
            return new Builder();
        }

        public Builder id(final Long id) {
            this.id = id;
            return this;
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Category convertToEntity(CategoryDTO categoryDTO) {
            Category category = new Category();
            category.setId(categoryDTO.getId());
            category.setName(categoryDTO.getName());
            return category;
        }

        public CategoryDTO build() {
            return new CategoryDTO(this);
        }
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

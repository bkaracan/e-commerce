package com.bkaracan.e_commerce.enumaration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageEnum {
  NOT_FOUND(1, "The record is not found!"),
  UNEXPECTED_ERROR(2, "Unexpected error!"),
  FETCH_SUCCESS(3,"Fetch success!"),
  SAVE_SUCCESS(4, "The record has been saved successfully!"),
  UPDATE_SUCCESS(5, "The record has been updated successfully!"),
  DELETE_SUCCESS(6, "The record has been deleted successfully!"),
  RECORD_EXISTS(7, "The record already exists!"),
  EMPTY_LIST(8, "The list is empty!"),
  NULL_OR_BLANK(9, "The value cannot be null or blank!");

  private final Integer code;
  private final String message;
}

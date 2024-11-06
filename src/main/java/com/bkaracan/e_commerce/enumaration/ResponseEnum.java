package com.bkaracan.e_commerce.enumaration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseEnum {
  OK(200, "OK", Boolean.TRUE),
  BAD_REQUEST(400, "BAD_REQUEST", Boolean.FALSE),
  UNAUTHORIZED(401, "UNAUTHORIZED", Boolean.FALSE),
  FORBIDDEN(403, "FORBIDDEN", Boolean.FALSE),
  NOT_FOUND(404, "NOT_FOUND", Boolean.FALSE),
  ERROR(1000, "ERROR", Boolean.FALSE),
  INFO(1002, "INFO", Boolean.TRUE),
  WARNING(100, "WARNING", Boolean.FALSE);

  private final Integer httpStatusCode;
  private final String description;
  private final Boolean isSuccess;
}

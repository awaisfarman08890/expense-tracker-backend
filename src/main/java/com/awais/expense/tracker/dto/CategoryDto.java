package com.awais.expense.tracker.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Category DTO (DATA Transfer Object) to transfer " +
                "the data between client and server"
)
public record CategoryDto(Long id,
                          @Schema(
                                  description = "Category name"
                          )
                          String name) {

}

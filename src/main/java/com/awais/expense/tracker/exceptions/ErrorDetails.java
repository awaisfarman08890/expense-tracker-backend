package com.awais.expense.tracker.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Schema(
        description = "Error Details DTO (DATA Transfer Object) to transfer " +
                "the error response between client and server"
)
public class ErrorDetails {
    @Schema(
            description = "Error occurred date time"
    )
    private LocalDateTime timestamp;
    @Schema(
            description = "Error message"
    )
    private String message;
    @Schema(
            description = "Error details"
    )
    private String details;
    @Schema(
            description = "Error code"
    )
    private String errorCode;

}

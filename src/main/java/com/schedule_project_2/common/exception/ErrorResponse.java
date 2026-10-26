package com.schedule_project_2.common.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.Map;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL) // errors가 null이면 JSON에서 아예 뺌
public class ErrorResponse {

    private final int status;
    private final String message;
    private final Map<String, String> errors; // 입력값 검증 실패일 때만 채움

    public ErrorResponse(int status, String message, Map<String, String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
}

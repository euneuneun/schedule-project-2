package com.schedule_project_2.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 400
    EMPTY_UPDATE(HttpStatus.BAD_REQUEST, "수정할 항목을 하나 이상 입력해주세요."),

    // 401
    LOGIN_REQUIRED(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다."),
    LOGIN_FAILED(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 올바르지 않습니다."),

    // 403
    USER_FORBIDDEN(HttpStatus.FORBIDDEN, "본인의 정보만 수정·삭제할 수 있습니다."),
    SCHEDULE_FORBIDDEN(HttpStatus.FORBIDDEN, "본인이 작성한 일정만 수정·삭제할 수 있습니다."),

    // 404
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),
    SCHEDULE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 일정입니다."),

    // 409
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "이미 사용 중인 이메일입니다.");

    private final HttpStatus status;
    private final String message;
}

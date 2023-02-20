package com.touchbiz.chatgpt.infrastructure.enums;


public enum ChatSessionInfoTypeEnum {

    QUESTION(1, "问题"),
    ANSWER(2, "回答");

    private final Integer code;
    private final String desc;

    ChatSessionInfoTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}

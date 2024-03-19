package com.sunnysunco.cloud.business.base.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseType {

    SUCCESS("success"),
    WARNING("warning"),
    INFO("info"),
    ERROR("error");

    private final String value;
}

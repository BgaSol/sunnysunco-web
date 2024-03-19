package com.sunnysunco.cloud.business.base.exception;

import com.sunnysunco.cloud.business.base.vo.BaseVo;
import com.sunnysunco.cloud.business.base.vo.ResponseType;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class BaseException extends RuntimeException {

    private final BaseVo<?> baseVo;

    public BaseException(BaseVo<?> baseVo) {
        super(baseVo.getMessage());
        this.baseVo = baseVo;
    }

    public BaseException(String message) {
        super(message);
        this.baseVo = BaseVo.error(message);
    }

    public BaseException(String message, ResponseType type) {
        super(message);
        this.baseVo = BaseVo.error(message, type);
    }
}

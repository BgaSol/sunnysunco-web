package com.sunnysunco.cloud.codegeneration.table.dto;

import com.sunnysunco.cloud.business.base.dto.BasePageDto;
import com.sunnysunco.cloud.codegeneration.table.TableEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "表分页查询参数")
public class TablePageDto extends BasePageDto<TableEntity> {
}

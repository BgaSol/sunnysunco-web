package com.sunnysunco.cloud.business.information.pdf.dto;

import com.sunnysunco.cloud.business.base.dto.BasePageDto;
import com.sunnysunco.cloud.business.information.pdf.PdfEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Schema(description = "pdf分页dto")
@Data
public class PdfPageDto extends BasePageDto<PdfEntity> {
}

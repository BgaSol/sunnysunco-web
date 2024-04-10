package com.sunnysunco.cloud.business.information.pdf.dto;

import com.sunnysunco.cloud.business.base.dto.BasePageDto;
import com.sunnysunco.cloud.business.information.pdf.PdfPageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Schema(description = "pdf页dto")
@Data
public class PdfPagePageDto extends BasePageDto<PdfPageEntity> {
}

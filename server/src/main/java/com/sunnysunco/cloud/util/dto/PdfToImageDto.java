package com.sunnysunco.cloud.util.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Pdf转图片Dto")
public class PdfToImageDto {

    @Schema(description = "pdf文件路径")
    String pdfFilePath;

    @Schema(description = "图片保存路径")
    String destDir;

    @Schema(description = "图片分辨率")
    Integer dpi;

    @Schema(description = "图片类型")
    String type;
}

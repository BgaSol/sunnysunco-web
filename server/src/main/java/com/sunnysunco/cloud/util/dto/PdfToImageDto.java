package com.sunnysunco.cloud.util.dto;

import lombok.Data;

@Data
public class PdfToImageDto {
    String pdfFilePath;
    String destDir;
    int dpi;
    String type;
}

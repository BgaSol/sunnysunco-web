package com.sunnysunco.cloud.business.information.pdf.dto;

import com.sunnysunco.cloud.business.auth.role.RoleEntity;
import lombok.Builder;
import lombok.Data;

import java.io.File;
import java.util.List;

@Data
@Builder
public class CreatePdfDto {
    private String type;
    private File file;
    private Integer sort;
    private List<RoleEntity> roles;
}

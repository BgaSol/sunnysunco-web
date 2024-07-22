package com.sunnysunco.cloud.business.auth.department.dto;

import com.sunnysunco.cloud.business.auth.clientpage.ClientPageEntity;
import com.sunnysunco.cloud.business.auth.department.DepartmentEntity;
import com.sunnysunco.cloud.business.base.dto.BaseUpdateDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "更新部门Dto")
public class UpdateDepartmentDto extends BaseUpdateDto<DepartmentEntity> {
    @Schema(description = "部门名")
    private String name;

    @Schema(description = "部门编码")
    private String code;

    @Schema(description = "部门域名")
    private String domain;

    @Schema(description = "部门页面ids")
    private List<String> pageIds;

    @Schema(description = "部门地址")
    private String address;

    @Schema(description = "部门电话")
    private String phone;

    @Schema(description = "部门备注HTML")
    private String html;

    @Schema(description = "部门图标id")
    private String iconId;

    @Schema(description = "父部门id")
    private String parentId;

    @Override
    public DepartmentEntity toEntity() {
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setName(name);
        departmentEntity.setCode(code);
        departmentEntity.setDomain(domain);
        if (pageIds != null) {
            Stream<ClientPageEntity> clientPageEntityStream = pageIds.stream().map((id) -> {
                ClientPageEntity clientPageEntity = new ClientPageEntity();
                clientPageEntity.setId(id);
                return clientPageEntity;
            });
            List<ClientPageEntity> clientPageEntities = clientPageEntityStream.collect(Collectors.toList());
            departmentEntity.setPages(clientPageEntities);
        }
        departmentEntity.setAddress(address);
        departmentEntity.setPhone(phone);
        departmentEntity.setHtml(html);
        departmentEntity.setIconId(iconId);
        departmentEntity.setParentId(parentId);
        return this.toEntity(departmentEntity);
    }
}

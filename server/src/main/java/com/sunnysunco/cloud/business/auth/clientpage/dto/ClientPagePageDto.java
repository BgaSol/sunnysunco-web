package com.sunnysunco.cloud.business.auth.clientpage.dto;

import com.sunnysunco.cloud.business.auth.clientpage.ClientPageEntity;
import com.sunnysunco.cloud.business.base.dto.PageDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Schema(description = "客户端自定义页面分页dto")
@Data
public class ClientPagePageDto extends PageDto<ClientPageEntity> {
    @Schema(description = "页面名称")
    private String name;

    @Schema(description = "页面描述")
    private String description;

    @Override
    public Specification<ClientPageEntity> getExample() {
        return (root, query, criteriaBuilder) -> {
            //用列表装载断言对象
            List<Predicate> predicates = new ArrayList<>();
            // 名称
            if (ObjectUtils.isNotEmpty(name)) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }
            // 描述
            if (ObjectUtils.isNotEmpty(description)) {
                predicates.add(criteriaBuilder.like(root.get("description"), "%" + description + "%"));
            }
            Predicate[] p = new Predicate[predicates.size()];
            return criteriaBuilder.and(predicates.toArray(p));
        };
    }
}

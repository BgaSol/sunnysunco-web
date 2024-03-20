package com.sunnysunco.cloud.business.information.image.dto;

import com.sunnysunco.cloud.business.base.dto.BasePageDto;
import com.sunnysunco.cloud.business.information.image.ImageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "图片分页查询参数")
public class ImagePageDto extends BasePageDto<ImageEntity> {
    @Schema(description = "图片名称")
    String name;

    @Schema(description = "图片类型")
    String type;

    @Override
    public Specification<ImageEntity> getExample() {
        return (root, query, criteriaBuilder) -> {
            //用列表装载断言对象
            List<Predicate> predicates = new ArrayList<>();
            // 名称
            if (ObjectUtils.isNotEmpty(name)) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }
            // 类型
            if (ObjectUtils.isNotEmpty(type)) {
                predicates.add(criteriaBuilder.like(root.get("type"), "%" + type + "%"));
            }
            Predicate[] p = new Predicate[predicates.size()];
            return criteriaBuilder.and(predicates.toArray(p));
        };
    }
}

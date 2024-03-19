package com.sunnysunco.cloud.business.information.tag.dto;

import com.sunnysunco.cloud.business.base.dto.PageDto;
import com.sunnysunco.cloud.business.information.tag.TagEntity;
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
@Schema(description = "标签分页实体类")
public class TagPageDto extends PageDto<TagEntity> {
    @Schema(description = "标签名称")
    String name;

    @Schema(description = "标签状态")
    String status;

    @Schema(description = "标签分组")
    String tagGroup;

    @Schema(description = "标签类型")
    String type;

    @Override
    public Specification<TagEntity> getExample() {
        return (root, query, criteriaBuilder) -> {
            //用列表装载断言对象
            List<Predicate> predicates = new ArrayList<>();
            // 名称
            if (ObjectUtils.isNotEmpty(name)) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }
            // 状态
            if (ObjectUtils.isNotEmpty(status)) {
                predicates.add(criteriaBuilder.like(root.get("status"), "%" + status + "%"));
            }
            // 分组
            if (ObjectUtils.isNotEmpty(tagGroup)) {
                predicates.add(criteriaBuilder.like(root.get("tagGroup"), "%" + tagGroup + "%"));
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

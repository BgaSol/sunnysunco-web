package com.sunnysunco.cloud.business.information.log.log.dto;

import com.sunnysunco.cloud.business.base.dto.BasePageDto;
import com.sunnysunco.cloud.business.information.log.log.LogEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Schema(description = "日志分页请求数据")
@Data()
public class LogPageDto extends BasePageDto<LogEntity> {
    @Schema(description = "用户名")
    private String username;

    @Schema(description = "客户端ip")
    private String ip;

    @Override
    @Schema(hidden = true)
    public Specification<LogEntity> getExample() {
        return (root, query, criteriaBuilder) -> {
            //用列表装载断言对象
            List<Predicate> predicates = new ArrayList<>();
            // 查询客户端ip
            if (ObjectUtils.isNotEmpty(ip)) {
                predicates.add(criteriaBuilder.like(root.get("ip"), "%" + ip + "%"));
            }
            // 查询用户名
            if (ObjectUtils.isNotEmpty(username)) {
                predicates.add(criteriaBuilder.like(root.get("user").get("username"), "%" + username + "%"));
            }
            Predicate[] p = new Predicate[predicates.size()];
            return criteriaBuilder.and(predicates.toArray(p));
        };
    }
}

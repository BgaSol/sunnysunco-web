package com.sunnysunco.cloud.business.auth.user.dto;

import com.sunnysunco.cloud.business.auth.user.UserEntity;
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
@Data
@Schema(description = "用户分页查询参数")
public class UserPageDto extends PageDto<UserEntity> {
    @Schema(description = "用户名")
    String username;

    @Schema(description = "昵称")
    String nickname;

    @Schema(description = "邮箱")
    String email;

    @Schema(description = "手机号")
    String phone;

    @Schema(description = "账户锁定")
    Boolean locked;

    @Schema(description = "部门id")
    String departmentId;

    @Override
    @Schema(hidden = true)
    public Specification<UserEntity> getExample() {
        return (root, query, criteriaBuilder) -> {
            //用列表装载断言对象
            List<Predicate> predicates = new ArrayList<>();
            // 查询用户名
            if (ObjectUtils.isNotEmpty(username)) {
                predicates.add(criteriaBuilder.like(root.get("username"), "%" + username + "%"));
            }
            // 查询昵称
            if (ObjectUtils.isNotEmpty(nickname)) {
                predicates.add(criteriaBuilder.like(root.get("nickname"), "%" + nickname + "%"));
            }
            // 查询邮箱
            if (ObjectUtils.isNotEmpty(email)) {
                predicates.add(criteriaBuilder.like(root.get("email"), "%" + email + "%"));
            }
            // 查询手机号
            if (ObjectUtils.isNotEmpty(phone)) {
                predicates.add(criteriaBuilder.like(root.get("phone"), "%" + phone + "%"));
            }
            // 查询账户锁定
            if (locked != null) {
                predicates.add(criteriaBuilder.equal(root.get("locked"), locked));
            }
            // 查询部门id
            if (ObjectUtils.isNotEmpty(departmentId)) {
                predicates.add(criteriaBuilder.equal(root.get("department").get("id"), departmentId));
            }
            Predicate[] p = new Predicate[predicates.size()];
            return criteriaBuilder.and(predicates.toArray(p));
        };
    }
}

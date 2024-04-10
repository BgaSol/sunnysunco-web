package com.sunnysunco.cloud.business.base;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data()
@MappedSuperclass()
public abstract class BaseTreeEntity<T extends BaseTreeEntity<T>> extends BaseEntity {
    @Schema(description = "父id")
    @TableField("parent_id")
    @Column(name = "parent_id")
    @Transient
    private String parentId;

    @Schema(description = "父实体")
    @TableField(exist = false)
    @JoinColumn(name = "parent_id")
    @ManyToOne()
    @JsonIgnore
    private T parent;

    @Schema(description = "子实体")
    @TableField(exist = false)
    @OneToMany(mappedBy = "parent")
    private List<T> children;

    @JsonGetter("parentId")
    public String parentId() {
        if (parent != null) {
            return parent.getId();
        }
        return parentId;
    }
}

package com.sunnysunco.cloud.business.information.tag;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sunnysunco.cloud.business.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data

@AllArgsConstructor
@NoArgsConstructor
@Builder

@Schema(description = "标签实体类")
@TableName("t_tag")
@Table(name = "t_tag")
@Entity()
public class TagEntity extends BaseEntity {
    @Schema(description = "标签名称")
    @TableField("name")
    @Column(name = "name")
    String name;

    @Schema(description = "标签状态")
    @TableField("status")
    @Column(name = "status")
    String status;

    @Schema(description = "标签分组")
    @TableField("tag_group")
    @Column(name = "tag_group")
    String tagGroup;
}

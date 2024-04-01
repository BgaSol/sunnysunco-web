package com.sunnysunco.cloud.business.information.image;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sunnysunco.cloud.business.base.BaseEntity;
import com.sunnysunco.cloud.business.information.file.FileEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data

@AllArgsConstructor
@NoArgsConstructor
@Builder

@TableName("t_image")
@Schema(description = "图片实体类")
@Table(name = "t_image")
@Entity()
public class ImageEntity extends BaseEntity {
    @TableField("name")
    @Schema(description = "图片名称")
    @Column(name = "name")
    String name;

    @Schema(description = "图片类型")
    @TableField("type")
    @Column(name = "type")
    String type;

    @Schema(description = "图片宽度")
    @TableField("width")
    @Column(name = "width")
    Integer width;

    @Schema(description = "图片高度")
    @TableField("height")
    @Column(name = "height")
    Integer height;

    @Schema(description = "图片文件id")
    @TableField("file_id")
    @Transient
    @Column(name = "file_id")
    String fileId;

    @TableField(exist = false)
    @Schema(description = "图片文件")
    @OneToOne(targetEntity = FileEntity.class)
    @JoinColumn(name = "file_id")
    FileEntity file;
}

package com.sunnysunco.cloud.business.information.file;

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

@TableName("t_file")
@Entity()
@Table(name = "t_file")
@Schema(description = "文件实体类")
public class FileEntity extends BaseEntity {
    @TableField("name")
    @Schema(description = "文件名称")
    @Column(name = "name")
    private String name;

    @Schema(description = "文件地址")
    @TableField("url")
    @Column(name = "url")
    private String url;

    @Schema(description = "文件类型")
    @TableField("type")
    @Column(name = "type")
    private String type;

    @Schema(description = "文件大小")
    @TableField("size")
    @Column(name = "size")
    private String size;

    @Schema(description = "文件hash")
    @TableField("hash")
    @Column(name = "hash")
    private String hash;

    @Schema(description = "文件状态")
    @TableField("status")
    @Column(name = "status")
    private String status;

    @Schema(description = "文件后缀")
    @TableField("suffix")
    @Column(name = "suffix")
    private String suffix;

    @Schema(description = "文件来源")
    @TableField("source")
    @Column(name = "source")
    private String source;

    @Schema(description = "文件所在minio桶")
    @TableField("bucket")
    @Column(name = "bucket")
    private String bucket;
}

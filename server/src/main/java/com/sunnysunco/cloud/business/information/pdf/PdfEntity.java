package com.sunnysunco.cloud.business.information.pdf;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sunnysunco.cloud.business.base.BaseEntity;
import com.sunnysunco.cloud.business.information.file.FileEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data

@AllArgsConstructor
@NoArgsConstructor
@Builder

@Schema(description = "PDF实体类")
@TableName("t_pdf")
@Table(name = "t_pdf")
@Entity()
public class PdfEntity extends BaseEntity {

    @Schema(description = "名称")
    @TableField("name")
    @Column(name = "name")
    private String name;

    @Schema(description = "文件id")
    @TableField("file_id")
    @Column(name = "file_id")
    @Transient
    private String fileId;

    @Schema(description = "文件")
    @TableField(exist = false)
    @OneToOne
    @JoinColumn(name = "file_id")
    private FileEntity file;

    @Schema(description = "页面列表")
    @OneToMany(mappedBy = "pdf")
    private List<PdfPageEntity> pages;

}

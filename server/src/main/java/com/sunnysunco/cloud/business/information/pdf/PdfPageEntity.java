package com.sunnysunco.cloud.business.information.pdf;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sunnysunco.cloud.business.base.BaseEntity;
import com.sunnysunco.cloud.business.information.image.ImageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data

@AllArgsConstructor
@NoArgsConstructor
@Builder

@Schema(description = "PDF页实体类")
@TableName("t_pdf_page")
@Table(name = "t_pdf_page")
@Entity()
public class PdfPageEntity extends BaseEntity {
    @Schema(description = "pdf页码")
    @TableField("page")
    @Column(name = "page")
    private Integer page;

    @Schema(description = "图片列表")
    @TableField("image_list")
    @ManyToMany()
    @JoinTable(name = "c_pdf_page_image",
            joinColumns = @JoinColumn(name = "pdf_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private List<ImageEntity> imageList;

    @Schema(description = "横向图片数量")
    @TableField("horizontal_image_count")
    @Column(name = "horizontal_image_count")
    private Integer horizontalImageCount;

    @Schema(description = "纵向图片数量")
    @TableField("vertical_image_count")
    @Column(name = "vertical_image_count")
    private Integer verticalImageCount;

    @Schema(description = "pdf")
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "pdf_id")
    private PdfEntity pdf;

    @Schema(description = "pdfid")
    @TableField("pdf_id")
    @Transient
    @Column(name = "pdf_id")
    private String pdfId;

}

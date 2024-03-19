package com.sunnysunco.cloud.business.auth.clientpage;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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

@TableName("t_client_page")
@Entity()
@Table(name = "t_client_page")
@Schema(description = "客户端页面实体类")
public class ClientPageEntity extends BaseEntity {
    @TableField("name")
    @Schema(description = "页面名称")
    @Column(name = "name")
    private String name;

    @OneToMany()
    @JoinTable(name = "c_client_page_image",
            joinColumns = @JoinColumn(name = "client_page_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    @TableField(exist = false)
    @OrderBy("createTime ASC")
    private List<ImageEntity> images;

    @Schema(description = "页面HTML")
    @TableField("html")
    @Column(name = "html")
    private String html;
}

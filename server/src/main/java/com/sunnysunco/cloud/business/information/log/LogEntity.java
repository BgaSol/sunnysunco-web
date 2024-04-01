package com.sunnysunco.cloud.business.information.log;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sunnysunco.cloud.business.auth.user.UserEntity;
import com.sunnysunco.cloud.business.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data

@AllArgsConstructor
@NoArgsConstructor
@Builder

@TableName("t_log")
@Entity()
@Table(name = "t_log")
@Schema(description = "日志实体类")
public class LogEntity extends BaseEntity {
    @Schema(description = "日志类型")
    @TableField("type")
    @Column(name = "type")
    private String type;

    @Schema(description = "请求地址")
    @TableField("url")
    @Column(name = "url", length = 4096)
    private String url;

    @Schema(description = "请求方法")
    @TableField("method")
    @Column(name = "method")
    private String method;

    @Schema(description = "请求来源")
    @TableField("referer")
    @Column(name = "referer")
    private String referer;

    @Schema(description = "客户端ip")
    @TableField("ip")
    @Column(name = "ip")
    private String ip;

    @Schema(description = "客户端浏览器")
    @TableField("browser")
    @Column(name = "browser")
    private String browser;

    @Schema(description = "客户端操作系统")
    @TableField("os")
    @Column(name = "os")
    private String os;

    @Schema(description = "用户id")
    @TableField("user_id")
    @Column(name = "user_id")
    @Transient
    private String userId;

    @Schema(description = "用户")
    @TableField(exist = false)
    @JoinColumn(name = "user_id")
    @ManyToOne()
    private UserEntity user;
}

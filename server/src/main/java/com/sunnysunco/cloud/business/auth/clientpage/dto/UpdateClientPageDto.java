package com.sunnysunco.cloud.business.auth.clientpage.dto;

import com.sunnysunco.cloud.business.auth.clientpage.ClientPageEntity;
import com.sunnysunco.cloud.business.base.dto.BaseUpdateDto;
import com.sunnysunco.cloud.business.information.image.ImageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.ObjectUtils;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@EqualsAndHashCode(callSuper = true)
@Schema(description = "更新客户端页面")
@Data
public class UpdateClientPageDto extends BaseUpdateDto<ClientPageEntity> {
    @Schema(description = "页面名称")
    @NotBlank(message = "页面名称不能为空")
    private String name;

    @Schema(description = "页面图片id列表")
    private List<String> imageIds;

    @Schema(description = "页面描述")
    @NotBlank(message = "页面描述不能为空")
    private String description;

    @Schema(description = "页面HTML")
    private String html;

    @Override
    public ClientPageEntity toEntity() {
        ClientPageEntity entity = new ClientPageEntity();
        entity.setName(name);
        entity.setHtml(html);
        if (ObjectUtils.isNotEmpty(imageIds)) {
            Stream<ImageEntity> imageEntityStream = imageIds.stream().map((id) -> {
                ImageEntity imageEntity = new ImageEntity();
                imageEntity.setId(id);
                return imageEntity;
            });
            List<ImageEntity> collect = imageEntityStream.collect(Collectors.toList());
            entity.setImages(collect);
        }
        return this.toEntity(entity);
    }
}

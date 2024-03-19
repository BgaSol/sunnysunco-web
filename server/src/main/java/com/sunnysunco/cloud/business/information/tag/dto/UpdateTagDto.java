package com.sunnysunco.cloud.business.information.tag.dto;

import com.sunnysunco.cloud.business.base.dto.BaseUpdateDto;
import com.sunnysunco.cloud.business.information.tag.TagEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data()
@Schema(description = "更新标签实体类")
public class UpdateTagDto extends BaseUpdateDto<TagEntity> {
    @NotBlank(message = "标签名称不能为空")
    @Schema(description = "标签名称")
    String name;

    @Schema(description = "标签状态")
    String status;

    @NotBlank(message = "标签分组不能为空")
    @Schema(description = "标签分组")
    String tagGroup;

    @NotBlank(message = "标签类型不能为空")
    @Schema(description = "标签类型")
    String type;

    @Override
    public TagEntity toEntity() {
        TagEntity tagEntity = new TagEntity();
        tagEntity.setName(name);
        tagEntity.setStatus(status);
        tagEntity.setTagGroup(tagGroup);
        tagEntity.setType(type);
        return super.toEntity(tagEntity);
    }
}

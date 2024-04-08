package ${packageName}.dto;

import com.sunnysunco.cloud.business.base.dto.BasePageDto;
import ${packageName}.${entityName};
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "${description} 分页查询参数")
public class ${entityName?replace("Entity", "PageDto")} extends BasePageDto<${entityName}> {
    // todo: add your custom fields here
}

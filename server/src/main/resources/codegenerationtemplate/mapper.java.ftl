package ${packageName};

import com.sunnysunco.cloud.business.base.BaseSCMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ${entityName?replace("Entity", "Mapper")} extends BaseSCMapper<${entityName}> {

}

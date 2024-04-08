package ${packageName};

import com.sunnysunco.cloud.business.base.BaseRepository;
import com.sunnysunco.cloud.business.base.BaseSCMapper;
import com.sunnysunco.cloud.business.base.BaseService;
import ${packageName}.dto.${entityName?replace("Entity", "PageDto")};
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class ${entityName?replace("Entity", "Service")} extends BaseService<${entityName}, ${entityName?replace("Entity", "PageDto")}> {

    private final ${entityName?replace("Entity", "Repository")} ${entityName?uncap_first?replace("Entity", "Repository")};

    private final ${entityName?replace("Entity", "Mapper")} ${entityName?uncap_first?replace("Entity", "Mapper")};

    @Override
    public ${entityName?replace("Entity", "Mapper")} commonBaseMapper() {
        return ${entityName?uncap_first?replace("Entity", "Mapper")};
    }

    @Override
    public ${entityName?replace("Entity", "Repository")} commonBaseRepository() {
        return ${entityName?uncap_first?replace("Entity", "Repository")};
    }
}

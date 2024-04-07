package ${packageName};

import com.sunnysunco.cloud.business.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ${entityName?replace("Entity", "Repository")} extends BaseRepository<${entityName}> {
}
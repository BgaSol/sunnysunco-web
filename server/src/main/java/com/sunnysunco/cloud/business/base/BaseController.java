package com.sunnysunco.cloud.business.base;

import com.sunnysunco.cloud.business.base.dto.BaseCreateDto;
import com.sunnysunco.cloud.business.base.dto.BaseUpdateDto;
import com.sunnysunco.cloud.business.base.dto.PageDto;
import com.sunnysunco.cloud.business.base.vo.BaseVo;
import com.sunnysunco.cloud.business.base.vo.PageVo;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
public abstract class BaseController<
        ENTITY extends BaseEntity,
        PAGEDTO extends PageDto<ENTITY>,
        CREATE_DTO extends BaseCreateDto<ENTITY>,
        UPDATE_DTO extends BaseUpdateDto<ENTITY>
        > {
    abstract public BaseService<ENTITY, PAGEDTO> commonBaseService();

    public BaseVo<PageVo<ENTITY>> findByPage(@Valid PAGEDTO pageDto) {
        PageVo<ENTITY> byPage = commonBaseService().findByPage(pageDto);
        return BaseVo.success(byPage);
    }

    public BaseVo<ENTITY> save(@Valid CREATE_DTO entity) {
        ENTITY save = commonBaseService().save(entity.toEntity());
        return BaseVo.success(save, "保存成功");
    }

    public BaseVo<ENTITY> update(@Valid UPDATE_DTO entity) {
        ENTITY update = commonBaseService().update(entity.toEntity());
        return BaseVo.success(update, "更新成功");
    }

    public BaseVo<Integer[]> delete(@Valid @NotBlank String ids) {
        String[] idsArr = ids.split(",");
        Integer[] delete = commonBaseService().delete(idsArr);
        return BaseVo.success(delete, "删除成功");
    }

    public BaseVo<ENTITY> findById(@Valid @NotBlank String id) {
        ENTITY byId = commonBaseService().findById(id);
        return BaseVo.success(byId);
    }

    public BaseVo<List<ENTITY>> findAll() {
        List<ENTITY> all = commonBaseService().findAll();
        return BaseVo.success(all);
    }

}

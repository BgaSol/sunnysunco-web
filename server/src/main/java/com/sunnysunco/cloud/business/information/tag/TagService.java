package com.sunnysunco.cloud.business.information.tag;

import com.sunnysunco.cloud.business.base.BaseService;
import com.sunnysunco.cloud.business.information.tag.dto.TagPageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TagService extends BaseService<TagEntity, TagPageDto> {
    private final TagMapper tagMapper;

    private final TagRepository tagRepository;

    @Override
    public Class<TagEntity> commonBaseEntityClass() {
        return TagEntity.class;
    }

    @Override
    public TagMapper commonBaseMapper() {
        return tagMapper;
    }

    @Override
    public TagRepository commonBaseRepository() {
        return tagRepository;
    }

}

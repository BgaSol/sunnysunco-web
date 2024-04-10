package com.sunnysunco.cloud.business.information.pdf;

import com.sunnysunco.cloud.business.base.BaseRepository;
import com.sunnysunco.cloud.business.base.BaseSCMapper;
import com.sunnysunco.cloud.business.base.BaseService;
import com.sunnysunco.cloud.business.information.pdf.dto.PdfPageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PdfService extends BaseService<PdfEntity, PdfPageDto> {

    private final PdfMapper pdfMapper;

    private final PdfRepository pdfRepository;

    private final PdfPageService pdfPageService;

    @Override
    public BaseSCMapper<PdfEntity> commonBaseMapper() {
        return pdfMapper;
    }

    @Override
    public BaseRepository<PdfEntity> commonBaseRepository() {
        return pdfRepository;
    }

    @Override
    public PdfEntity save(PdfEntity entity) {
        entity.getPages().forEach(pdfPageService::save);
        return super.save(entity);
    }

    @Override
    public PdfEntity update(PdfEntity entity) {
        throw new UnsupportedOperationException("无法更新Pdf");
    }

    @Override
    public Integer[] delete(String... ids) {
        for (String id : ids) {
            String[] deleteIds = this.findById(id).getPages().stream().map(PdfPageEntity::getId).toArray(String[]::new);
            pdfPageService.delete(deleteIds);
        }
        return super.delete(ids);
    }
}

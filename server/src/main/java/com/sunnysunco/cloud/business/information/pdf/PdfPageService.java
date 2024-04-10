package com.sunnysunco.cloud.business.information.pdf;

import com.sunnysunco.cloud.business.base.BaseRepository;
import com.sunnysunco.cloud.business.base.BaseSCMapper;
import com.sunnysunco.cloud.business.base.BaseService;
import com.sunnysunco.cloud.business.information.pdf.dto.PdfPagePageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PdfPageService extends BaseService<PdfPageEntity, PdfPagePageDto> {

    private final PdfPageMapper pdfPageMapper;

    private final PdfPageRepository pdfPageRepository;

    @Override
    public BaseSCMapper<PdfPageEntity> commonBaseMapper() {
        return pdfPageMapper;
    }

    @Override
    public BaseRepository<PdfPageEntity> commonBaseRepository() {
        return pdfPageRepository;
    }
}

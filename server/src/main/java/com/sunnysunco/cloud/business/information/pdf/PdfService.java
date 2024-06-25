package com.sunnysunco.cloud.business.information.pdf;

import com.sunnysunco.cloud.business.base.BaseRepository;
import com.sunnysunco.cloud.business.base.BaseSCMapper;
import com.sunnysunco.cloud.business.base.BaseService;
import com.sunnysunco.cloud.business.base.dto.BaseCreateDto;
import com.sunnysunco.cloud.business.information.file.FileEntity;
import com.sunnysunco.cloud.business.information.file.FileService;
import com.sunnysunco.cloud.business.information.image.ImageEntity;
import com.sunnysunco.cloud.business.information.image.ImageService;
import com.sunnysunco.cloud.business.information.pdf.dto.CreatePdfDto;
import com.sunnysunco.cloud.business.information.pdf.dto.PdfPageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class PdfService extends BaseService<PdfEntity, PdfPageDto> {

    private final PdfMapper pdfMapper;

    private final PdfRepository pdfRepository;

    private final PdfPageService pdfPageService;

    private final ImageService imageService;

    private final FileService fileService;

    @Override
    public BaseSCMapper<PdfEntity> commonBaseMapper() {
        return pdfMapper;
    }

    @Override
    public BaseRepository<PdfEntity> commonBaseRepository() {
        return pdfRepository;
    }

    @Override
    @Transactional
    public PdfEntity save(PdfEntity entity) {
        entity.getPages().forEach(pdfPageService::save);
        return super.save(entity);
    }

    @Override
    @Transactional
    public Integer[] delete(String... ids) {
        List<PdfPageEntity> pdfPageEntities = new ArrayList<>();
        for (String id : ids) {
            PdfEntity pdfEntity = this.findById(id);
            pdfPageEntities.addAll(pdfEntity.getPages());
        }
        pdfPageService.delete(pdfPageEntities.stream().map(PdfPageEntity::getId).toArray(String[]::new));
        return super.delete(ids);
    }

    @Transactional
    public PdfEntity save(CreatePdfDto createPdfDto) {
        File courseware = createPdfDto.getFile();

        // 上传文件
        FileEntity pdfFileEntity = fileService.save(courseware, null);

        String imagesPath = courseware.getAbsolutePath() + ".images";
        File[] images = Arrays.stream(Objects.requireNonNull(new File(imagesPath).listFiles())).filter(f -> f.getName().endsWith(".jpg")).toArray(File[]::new);

        // 排序
        images = Arrays.stream(images).sorted((f1, f2) -> {
            String name1 = f1.getName().split("\\.")[0];
            String name2 = f2.getName().split("\\.")[0];
            return Integer.parseInt(name1) - Integer.parseInt(name2);
        }).toArray(File[]::new);

        PdfEntity pdfEntity = new PdfEntity();
        pdfEntity.setId(BaseCreateDto.staticInitId(pdfEntity));

        pdfEntity.setAccessRole(createPdfDto.getRoles());
        pdfEntity.setFile(pdfFileEntity);
        pdfEntity.setFileId(pdfFileEntity.getId());

        pdfEntity.setType(createPdfDto.getType());
        pdfEntity.setSort(createPdfDto.getSort());

        pdfEntity.setPages(new ArrayList<>());
        for (File image : images) {
            // 创建pdf页面
            PdfPageEntity pdfPageEntity = new PdfPageEntity();
            pdfPageEntity.setId(BaseCreateDto.staticInitId(pdfPageEntity));

            pdfPageEntity.setHorizontalImageCount(1);
            pdfPageEntity.setVerticalImageCount(1);
            // 上传图片
            ImageEntity imageEntity = imageService.save(image);
            pdfPageEntity.setImageList(Collections.singletonList(imageEntity));

            pdfEntity.getPages().add(pdfPageEntity);
        }
        return this.save(pdfEntity);
    }
}

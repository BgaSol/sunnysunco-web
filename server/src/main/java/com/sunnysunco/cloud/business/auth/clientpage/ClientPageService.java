package com.sunnysunco.cloud.business.auth.clientpage;

import com.sunnysunco.cloud.business.auth.clientpage.dto.ClientPagePageDto;
import com.sunnysunco.cloud.business.auth.department.DepartmentEntity;
import com.sunnysunco.cloud.business.auth.department.DepartmentService;
import com.sunnysunco.cloud.business.base.BaseService;
import com.sunnysunco.cloud.business.base.exception.BaseException;
import com.sunnysunco.cloud.business.information.image.ImageEntity;
import com.sunnysunco.cloud.business.information.image.ImageService;
import io.minio.GetObjectResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ClientPageService extends BaseService<ClientPageEntity, ClientPagePageDto> {
    private final ClientPageMapper clientPageMapper;

    private final ClientPageRepository clientPageRepository;

    private final DepartmentService departmentService;

    private final ImageService imageService;

    @Override
    public ClientPageMapper commonBaseMapper() {
        return clientPageMapper;
    }

    @Override
    public ClientPageRepository commonBaseRepository() {
        return clientPageRepository;
    }

    @Transactional(readOnly = true)
    public List<ClientPageEntity> getMyClientPages(String domain) {
        DepartmentEntity myDepartment = departmentService.getMyDepartment(domain);
        return myDepartment.getPages();
    }

    public GetObjectResponse getMyClientPageImage(String domain, String imageId) {
        DepartmentEntity myDepartment = departmentService.getMyDepartment(domain);
        List<ClientPageEntity> pages = myDepartment.getPages();
        for (ClientPageEntity page : pages) {
            for (ImageEntity image : page.getImages()) {
                if (image.getId().equals(imageId)) {
                    return imageService.getImageStream(imageId);
                }
            }
        }
        throw new BaseException("图片不存在");
    }
}
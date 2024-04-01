package com.sunnysunco.cloud.codegeneration;

import com.sunnysunco.cloud.codegeneration.table.TableEntity;
import com.sunnysunco.cloud.codegeneration.table.TableService;
import com.sunnysunco.cloud.codegeneration.tablecolum.TableColumnEntity;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GenerationUtil implements ApplicationRunner {

    private final TableService tableService;
    private final ResourceLoader resourceLoader;

    @Override
    @Transactional(readOnly = true)
    public void run(ApplicationArguments args) throws IOException, TemplateException {
        List<TableEntity> all = tableService.findAll();
        TableEntity tableEntity = all.get(0);
        List<TableColumnEntity> tableColumns = tableEntity.getTableColumns();
        System.out.println(tableColumns.size());

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        Resource resource = resourceLoader.getResource("classpath:codegenerationtemplate");
        configuration.setDirectoryForTemplateLoading(resource.getFile());

        Template template = configuration.getTemplate("entity.java");

        StringWriter stringWriter = new StringWriter();
        template.process(tableEntity, stringWriter);

        String result = stringWriter.toString();
        System.out.println(result);
    }
}

package com.sunnysunco.cloud.codegeneration;

import com.sunnysunco.cloud.codegeneration.table.TableEntity;
import com.sunnysunco.cloud.codegeneration.table.TableService;
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
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GenerationUtil implements ApplicationRunner {

    private final TableService tableService;
    private final ResourceLoader resourceLoader;

    @Override
    @Transactional(readOnly = true)
    public void run(ApplicationArguments args) throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        Resource resource = resourceLoader.getResource("classpath:codegenerationtemplate");
        configuration.setDirectoryForTemplateLoading(resource.getFile());

        List<TableEntity> all = tableService.findAll();
        // entity
        for (int i = 0; i < 3; i++) {
            TableEntity tableEntity = all.get(i);

            Template template = configuration.getTemplate("entity.java.ftl");

            String result = FreeMarkerTemplateUtils.processTemplateIntoString(template, tableEntity);
            Path dir = Paths.get("./code_generation");
            if (!Files.exists(dir)) {
                Files.createDirectory(dir);
            }
            Files.write(Paths.get("./code_generation/" + tableEntity.getEntityName() + ".java"), result.getBytes(StandardCharsets.UTF_8));
        }
        // mapper
        for (int i = 0; i < 3; i++) {
            TableEntity tableEntity = all.get(i);

            Template template = configuration.getTemplate("mapper.java.ftl");

            String result = FreeMarkerTemplateUtils.processTemplateIntoString(template, tableEntity);
            Path dir = Paths.get("./code_generation");
            if (!Files.exists(dir)) {
                Files.createDirectory(dir);
            }
            Files.write(Paths.get("./code_generation/" + tableEntity.getEntityName().replace("Entity", "Mapper") + ".java"), result.getBytes(StandardCharsets.UTF_8));
        }
        // repository
        for (int i = 0; i < 3; i++) {
            TableEntity tableEntity = all.get(i);

            Template template = configuration.getTemplate("repository.java.ftl");

            String result = FreeMarkerTemplateUtils.processTemplateIntoString(template, tableEntity);
            Path dir = Paths.get("./code_generation");
            if (!Files.exists(dir)) {
                Files.createDirectory(dir);
            }
            Files.write(Paths.get("./code_generation/" + tableEntity.getEntityName().replace("Entity", "Repository") + ".java"), result.getBytes(StandardCharsets.UTF_8));
        }
    }
}

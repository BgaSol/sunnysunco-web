package com.sunnysunco.cloud.config.minio;


import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Minio配置
 */
@Configuration
@Data
public class MinioConfig {

    @Value("${minio.bucket}")
    public String bucket;

    @Value("${minio.url}")
    private String minioUrl;

    @Value("${minio.username}")
    private String minioUsername;

    @Value("${minio.password}")
    private String minioPassword;

    /**
     * 注入Minio客户端
     */
    @Bean
    public MinioClient minioClient() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        // 创建MinIO的Java客户端
        MinioClient client = MinioClient.builder()
                .endpoint(minioUrl)
                .credentials(minioUsername, minioPassword)
                .build();
        // 检查存储桶是否已经存在
        BucketExistsArgs bucketExistsArgs = BucketExistsArgs.builder().bucket(this.bucket).build();
        if (!client.bucketExists(bucketExistsArgs)) {
            // 如果不存在就创建一个存储桶
            MakeBucketArgs makeBucketArgs = MakeBucketArgs.builder().bucket(this.bucket).build();
            client.makeBucket(makeBucketArgs);
        }
        return client;
    }
}

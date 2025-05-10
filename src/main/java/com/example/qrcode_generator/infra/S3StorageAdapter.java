package com.example.qrcode_generator.infra;

import com.example.qrcode_generator.ports.StoragePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.net.URI;

@Component
public class S3StorageAdapter implements StoragePort {

    private final S3Client s3Client;
    private final String bucketName;
    private final String region;
    private final String endpoint;


    public S3StorageAdapter(@Value("${aws.s3.bucket.name}")String bucketName,
                            @Value("${aws.s3.region}")String region,
                            @Value("${aws.s3.endpoint}")String endpoint) {
        this.bucketName = bucketName;
        this.region = region;
        this.endpoint = endpoint;
        this.s3Client = S3Client.builder()
                .region(Region.of(this.region))
                .endpointOverride(URI.create(endpoint))
                .serviceConfiguration(S3Configuration.builder()
                        .pathStyleAccessEnabled(true)
                        .build())
                .build();
    }
    @Override
    public String uploadFile(byte[] fileData, String fileName, String contentType) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType(contentType)
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(fileData));

        // Retorno para usar localstack como simulador da AWS S3
        return String.format("%s/%s/%s", endpoint, bucketName, fileName);

//        *Caso use a AWS S3 padrão, descomente a linha abaixo e comente a linha acima*
//        return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, fileName);
    }

}

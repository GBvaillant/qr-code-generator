package com.example.qrcode_generator.controller;

import com.example.qrcode_generator.dto.QrCodeGeneratorRequest;
import com.example.qrcode_generator.dto.QrCodeGeneratorResponse;
import com.example.qrcode_generator.service.QrCodeGeneratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {

    private  final QrCodeGeneratorService qrCodeGeneratorService;

    public QrCodeController(QrCodeGeneratorService qrCodeGeneratorService) {
        this.qrCodeGeneratorService = qrCodeGeneratorService;
    }

    @PostMapping
    public ResponseEntity<QrCodeGeneratorResponse> generator (@RequestBody QrCodeGeneratorRequest request) {
        try {
            QrCodeGeneratorResponse response = this.qrCodeGeneratorService.generatorAndUploadQrCode(request.text());
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}

package com.example.qrcode_generator.controller;

import com.example.qrcode_generator.dto.QrCodeGeneratorRequest;
import com.example.qrcode_generator.dto.QrCodeGeneratorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {

    public ResponseEntity<QrCodeGeneratorResponse> generator (
            @RequestBody QrCodeGeneratorRequest request) {
        return null;
    }
}

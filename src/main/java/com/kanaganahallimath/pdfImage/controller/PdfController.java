package com.kanaganahallimath.pdfImage.controller;

import com.kanaganahallimath.pdfImage.service.PdfToImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@RestController
@RequestMapping("/api/pdf")
public class PdfController {
    private static final Logger logger = LoggerFactory.getLogger(PdfController.class);
    @Autowired
    private PdfToImageService pdfToImageService;

    @GetMapping("check")
    public String check(){
        return "working";
    }
    @PostMapping("/convert")
    public ResponseEntity<byte[]> convertPdfToImage(@RequestParam("file") MultipartFile file) {
        logger.info("started conversion"+pdfToImageService);
        try {
            byte[] imageBytes = pdfToImageService.convertPdfFirstPageToImage(file.getInputStream());
            logger.info("fetched bytes");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.IMAGE_PNG);
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

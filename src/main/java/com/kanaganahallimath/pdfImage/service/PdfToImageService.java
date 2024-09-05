package com.kanaganahallimath.pdfImage.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


@Service
public class PdfToImageService {

    private static final Logger logger = LoggerFactory.getLogger(PdfToImageService.class);


    public byte[] convertPdfFirstPageToImage(InputStream pdfInputStream) throws IOException {
        logger.info("started the service");
        try (PDDocument document = PDDocument.load(pdfInputStream)) {
            logger.info("started image creation");
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            logger.info("PDFRenderer");
            BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(0, 300); // 0 for the first page, 300 DPI
            logger.info("inside service");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);
            return baos.toByteArray();
        }
    }

}

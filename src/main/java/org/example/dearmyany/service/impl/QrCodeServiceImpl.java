package org.example.dearmyany.service.impl;

import org.example.dearmyany.service.QrCodeService;
import org.springframework.stereotype.Service;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.ByteArrayOutputStream;


@Service
public class QrCodeServiceImpl implements QrCodeService {

    @Override
    public byte[] createQrCode(String url, int width, int height) throws Exception{
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, width, height);

        try (ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream()) {
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            return pngOutputStream.toByteArray();
        }
    }
}

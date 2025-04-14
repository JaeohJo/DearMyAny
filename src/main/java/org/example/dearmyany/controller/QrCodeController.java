package org.example.dearmyany.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.example.dearmyany.service.QrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QrCodeController {

    private final QrCodeService qrCodeService;

    @Autowired
    public QrCodeController(QrCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @GetMapping("/makeQrCode")
    public void getQrCode(@RequestParam String url, HttpServletResponse response){
        try {
            byte[] qrImage = qrCodeService.createQrCode(url, 300, 300);
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
            response.getOutputStream().write(qrImage);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}

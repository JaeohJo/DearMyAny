package org.example.dearmyany.service;

public interface QrCodeService {

    public byte[] createQrCode(String url, int width, int height) throws Exception;

}

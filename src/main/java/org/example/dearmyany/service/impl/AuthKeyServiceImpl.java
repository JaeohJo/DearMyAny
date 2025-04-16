package org.example.dearmyany.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dearmyany.dto.AuthKeyDto;
import org.example.dearmyany.entity.AuthKey;
import org.example.dearmyany.repository.AuthKeyRepository;
import org.example.dearmyany.service.AuthKeyService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class AuthKeyServiceImpl implements AuthKeyService {

    private final AuthKeyRepository authKeyRepository;

    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final Random random = new SecureRandom();

    @Override
    public List<AuthKeyDto> generateAuthKeys(int count) {
        List<AuthKey> keys = IntStream.range(0, count)
                .mapToObj(i -> AuthKey.builder()
                        .authKey(generateRandomKey())
                        .used(false)
                        .createdAt(LocalDateTime.now())
                        .build())
                .collect(Collectors.toList());

        List<AuthKey> saved = authKeyRepository.saveAll(keys);

        return saved.stream()
                .map(k -> AuthKeyDto.builder()
                        .id(k.getId())
                        .authKey(k.getAuthKey())
                        .used(k.isUsed())
                        .createdAt(k.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValidKey(String authKey) {
        return authKeyRepository.findByAuthKeyAndUsedFalse(authKey).isPresent();
    }

    private String generateRandomKey() {
        String part1 = randomString(4);
        String part2 = randomString(4);
        return "KEY-" + part1 + "-" + part2;
    }

    private String randomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }
        return sb.toString();
    }

    @Override
    public void markKeyAsUsed(String authKey) {
        authKeyRepository.findByAuthKeyAndUsedFalse(authKey).ifPresent(key -> {
            key.setUsed(true);
            authKeyRepository.save(key);
        });
    }
}
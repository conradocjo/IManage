package br.imanage.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.util.Base64.getDecoder;
import static java.util.Base64.getEncoder;
import static java.util.UUID.randomUUID;

@Service
@Slf4j
public class HashServiceImpl implements HashService {

    /**
     * Gera Hash MD5
     */
    @Override
    public String getHashedPassword(String vault) throws NoSuchAlgorithmException {
        var hash = MessageDigest.getInstance("MD5");
        hash.update(vault.getBytes());
        var digest = hash.digest();
        return DatatypeConverter
                .printHexBinary(digest).toUpperCase();
    }

    /**
     * Encripta Senha
     * */
    @Override
    public String encodePass(String vault) {
        return keyWord() + getEncoder().encodeToString(vault.getBytes());
    }

    @Override
    public String encodeParcialPass(String vault) {
        return getEncoder().encodeToString(vault.getBytes());
    }

    /**
     * Decripta Senha
     * */
    @Override
    public String decoderPass(String test) {
        return new String(getDecoder().decode(test.substring(8, test.length())));
    }

    private static String keyWord() {
        return randomUUID().toString().split("-")[0];
    }

}

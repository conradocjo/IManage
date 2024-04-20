package br.imanage.service;

import java.security.NoSuchAlgorithmException;

public interface HashService {
    String getHashedPassword(String vault) throws NoSuchAlgorithmException;

    String encodePass(String vault);

    String encodeParcialPass(String vault);

    String decoderPass(String test);
}

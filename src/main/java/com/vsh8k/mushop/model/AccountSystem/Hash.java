package com.vsh8k.mushop.model.AccountSystem;

import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Hash {
    public static String getHash(String username, String password) {
        String b64user = Base64.getEncoder().encodeToString(username.getBytes(StandardCharsets.UTF_8));
        String b64pass = Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8));
        b64user = Hex.encodeHexString(b64user.getBytes());
        b64pass = Hex.encodeHexString(b64pass.getBytes());
        String hash = Base64.getEncoder().encodeToString((b64user+b64pass).getBytes(StandardCharsets.UTF_8));
        hash = Hex.encodeHexString(hash.getBytes());
        System.out.println(hash);
        return hash;
    }
}

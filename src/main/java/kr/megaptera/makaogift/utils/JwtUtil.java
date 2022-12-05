package kr.megaptera.makaogift.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {
    private Algorithm algorithm;

    public JwtUtil(String secret) {
        this.algorithm = Algorithm.HMAC256(secret);
    }

    public String encode(String username) {
        String token = JWT.create()
                .withClaim("username", username)
                .sign(algorithm);

        return token;
    }

    public String decode(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT verify = verifier.verify(token);

        String value = verify.getClaim("username").asString();

        return value;
    }
}

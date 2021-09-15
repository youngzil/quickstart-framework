package org.quickstart.crypto.jsonwebtoken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Test {

    public static void main(String[] args) {
        Key key = MacProvider.generateKey();
        String keyStr = Base64.getEncoder().encodeToString(key.getEncoded());

        Map<String, Object> headers = new HashMap<>();
        headers.put("userId", "testuserid");//token中放入userId
        headers.put("ip", "ip");
        headers.put("topic", "testTopic");
        headers.put("appName", "testApp");
        headers.put("type", "producer");

        long ff = System.currentTimeMillis() + 30 * 1000;
        System.out.println(ff);
        String compactJws = Jwts.builder().setIssuer("jsonwebTokenApp").setHeader(headers).setExpiration(new Date(ff))
            .signWith(SignatureAlgorithm.HS256, Base64.getDecoder().decode(keyStr)).compact();

        JwsHeader headers2 = null;
        Claims claims = null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(Base64.getDecoder().decode(keyStr)).parseClaimsJws(compactJws);
        headers2 = claimsJws.getHeader();
        claims = claimsJws.getBody();

        String tokenIp = (String)headers2.get("ip");
        String tokenTopic = (String)headers2.get("topic");
        String tokenAppName = (String)headers2.get("appName");
        String tokenType = (String)headers2.get("type");

        long date2 = claims.getExpiration().getTime();
        System.out.println("");

    }

    @org.junit.Test
    public void testSimple(){
        // We need a signing key, so we'll create one just for this example. Usually
        // the key would be read from your application configuration instead.
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String jws = Jwts.builder().setSubject("Joe").signWith(key).compact();

        String jws2 = Jwts.builder()
            .setIssuer("me")
            .setSubject("Bob")
            .setAudience("you")
            // .setExpiration(expiration) //a java.util.Date
            // .setNotBefore(notBefore) //a java.util.Date
            .setIssuedAt(new Date()) // for example, now
            .setId(UUID.randomUUID().toString()) //just an example id
            .compact();

        // 解密
        assert Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jws).getBody().getSubject().equals("Joe");

        try {

            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jws);

            //OK, we can trust this JWT

        } catch (JwtException e) {

            //don't trust the JWT!
        }
    }
}

package com.base.config.jwt;

import com.base.config.handler.SecurityConstant;
import com.base.util.StringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JWTUtil {

    private static final String SECRET = "MyJwtSecret";
    private static final int EXPIRATION = 7 * 24 * 60 * 60 * 1000;//有效期7天

    /**
     * 根据用户名生成token
     *
     * @param name 用户名
     * @return token
     */
    public static String builderToken(String name) {
        String token = Jwts.builder()
                .setSubject(name)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return SecurityConstant.TOKEN_SUFFIX + token;
    }

    /**
     * 检验token是否过期
     *
     * @return true-没过期,false-过期
     */
    public static boolean checkToken(String token) {
        Claims claims = getBody(token);

        //得到过期时间
        Date expiration = claims.getExpiration();
        long now = System.currentTimeMillis();
        if (now > expiration.getTime()) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * 得到用户名
     *
     * @return username
     */
    public static String getUsernameFromToken(String token) {
        Claims claims = getBody(token);

        return claims.getSubject();
    }

    private static Claims getBody(String token) {
        return Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(StringUtil.removeHeader(token, SecurityConstant.TOKEN_SUFFIX))
                .getBody();
    }

}

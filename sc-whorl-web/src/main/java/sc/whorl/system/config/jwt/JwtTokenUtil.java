package sc.whorl.system.config.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

/***
 *
 * @FileName: JwtTokenUtil
 * @remark: jwt工具类  提供校验toeken 、生成token、根据token获取用户等方法
 *
 */
@Component
@Slf4j
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -5883980282405596071L;

    public static final ThreadLocal<JWTUserDetail> LOCAL_USER = new ThreadLocal<>();

    public final static String JWT_TOKEN_PREFIX = "jwt:%s:%d";


    private final String JWT_LOGIN_NAME = "JWT_LOGIN_NAME";
    private final String JWT_LOGIN_TIME = "JWT_LOGIN_TIME";
    private final String JWT_LOGIN_USERID = "JWT_LOGIN_USERID";
    private final String JWT_LOGIN_USERTYPE = "JWT_LOGIN_USERTYPE";

    //签名方式
    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    //密匙
    @Value("${jwt.security.secret}")
    private String secret;

    @Value("${jwt.access_token:#{30*24*60*60}}")
    private Long access_token_expiration;


    public String getLoginNameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    /**
     * 根据token 获取用户信息
     */
    public JWTUserDetail getUserFromToken(String token) {
            JWTUserDetail jwtUserDetails = new JWTUserDetail();
            Claims claims = getClaimsFromToken(token);
        jwtUserDetails.setUserId(claims.get(JWT_LOGIN_USERID, Long.class));
        jwtUserDetails.setLoginName(claims.get(JWT_LOGIN_NAME, String.class));
        jwtUserDetails.setUserType(Enum.valueOf(JWTUserDetail.UserType.class, (String) claims.get(JWT_LOGIN_USERTYPE)));
        jwtUserDetails.setLoginTime(new Date(claims.get(JWT_LOGIN_TIME, Long.class)));
        jwtUserDetails.setJwtToken(token);
        return jwtUserDetails;

    }


    /**
     * 根据用户信息生成token
     * @param user
     * @return
     */
    public String generateToken(JWTUserDetail user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(JWT_LOGIN_NAME, user.getLoginName());
        claims.put(JWT_LOGIN_TIME, user.getLoginTime());
        claims.put(JWT_LOGIN_USERID, user.getUserId());
        claims.put(JWT_LOGIN_USERTYPE, user.getUserType());
        return Jwts.builder()
                //一个map 可以资源存放东西进去
                .setClaims(claims)
                //  用户名写入标题
                .setSubject(user.getLoginName())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date())
                //过期时间
                .setExpiration(new Date(System.currentTimeMillis() + access_token_expiration * 1000))
                //数字签名
                .signWith(SIGNATURE_ALGORITHM, secret)
                .compact();
    }




    /**
     * 根据token 获取生成时间
     */
    public Date getCreatedDateFromToken(String token) {
        return getClaimsFromToken(token).getIssuedAt();
    }

    /**
     * 根据token 获取过期时间
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    /**
     * token 是否过期
     */
    public Boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }


    /***
     * 解析token 信息
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                    //签名的key
                    .setSigningKey(secret)
                    // 签名token
                    .parseClaimsJws(token)
                    .getBody();
    }
}

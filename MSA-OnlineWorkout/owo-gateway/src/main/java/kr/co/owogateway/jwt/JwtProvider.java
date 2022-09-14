package kr.co.owogateway.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import kr.co.owocommon.error.exception.UserDefineException;
import kr.co.owocommon.error.jwt.JwtTokenExpiredException;
import kr.co.owocommon.error.jwt.JwtTokenInvalidException;
import kr.co.owogateway.jwt.enums.MemberRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class JwtProvider {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    final String MEMBER_IDENTITY = "identity";
    final String MEMBER_ROLE = "member_role";

    /**
     * 토큰의 유효성을 판단하는 메소드
     * @param token : 토큰
     * @return 토큰이 만료되었는지에 대한 불리언 값
     * @exception ExpiredJwtException 토큰이 만료되었을 경우에 발생하는 예외
     */
    public boolean isUsable(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(generateKey())
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT Signature");
            throw new JwtTokenInvalidException("Invalid JWT Signature");
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token");
            throw new JwtTokenInvalidException("Invalid JWT Token");
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token");
            throw new JwtTokenExpiredException();
        } catch (IllegalArgumentException e) {
            log.error("Empty JWT Claims");
            throw new JwtTokenInvalidException("Empty JWT Claims");
        }
    }

    /**
     * 키 변환을 위한 key 를 만드는 메서드
     * @return secret key
     */
    private byte[] generateKey(){
        try{
            return SECRET_KEY.getBytes("UTF-8");
        }catch (UnsupportedEncodingException e){
            throw new UserDefineException("키 변환에 실패하였습니다. ", e.getMessage());
        }
    }

    public Optional<String> resolveToken(ServerHttpRequest request){
        return Optional.of(request.getHeaders()
                .get("Authorization").get(0)
                .replace("Bearer", "").trim());
    }

    /**
     * 토큰을 통해서 Authentication 객체를 만들어내는 메서드
     * @param token 토큰
     * @return 사용자 정보를 담은 UsernamePasswordAuthenticationToken 객체
     */
    public Authentication getAuthentication(String token){
        UserDetails userDetails =
                new User(findIdentityByToken(token),
                        "",
                        getAuthorities(MemberRole.of(findRoleByToken(token))));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * 토큰을 이용하여 사용자 아이디를 찾는 메서드
     * @param token 토큰
     * @return 사용자의 아이디
     */
    public String findIdentityByToken(String token){
        return (String) Jwts.parser()
                .setSigningKey(generateKey())
                .parseClaimsJws(token)
                .getBody()
                .get(MEMBER_IDENTITY);
    }

    /**
     * 토큰을 이용하여 사용자 권한을 찾는 메서드
     * @param token 토큰
     * @return 사용자의 권한
     */
    public String findRoleByToken(String token){
        return (String) Jwts.parser()
                .setSigningKey(generateKey())
                .parseClaimsJws(token)
                .getBody()
                .get(MEMBER_ROLE);
    }

    private Set<? extends GrantedAuthority> getAuthorities(MemberRole role) {
        Set<GrantedAuthority> set = new HashSet<>();

        if(role.equals(MemberRole.ROLE_ADMIN)){
            set.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        set.add(new SimpleGrantedAuthority("ROLE_MEMBER"));

        return set;
    }

}

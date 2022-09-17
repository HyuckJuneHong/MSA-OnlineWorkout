package kr.co.owomember.infra.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.owomember.domain.dto.MemberDto;
import kr.co.owomember.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final MemberService memberService;
    private final Environment env;

    @Autowired
    public AuthenticationFilter(AuthenticationManager authenticationManager,
                                MemberService memberService,
                                Environment env) {
        super.setAuthenticationManager(authenticationManager);
        this.memberService = memberService;
        this.env = env;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {
            MemberDto.LOGIN login
                    = new ObjectMapper().readValue(request.getInputStream(), MemberDto.LOGIN.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            login.getIdentity(),
                            login.getPassword(),
                            new ArrayList<>()
                    )
            );
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        String memberName = ((User)authResult.getPrincipal()).getUsername();
        MemberDto.DETAILS_MEMBER member = memberService.getMemberDetailsByIdentity(memberName);
    }
}

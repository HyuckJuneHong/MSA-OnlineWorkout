package kr.co.owomember.infra.interceptor;

import kr.co.owocommon.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             @NotNull HttpServletResponse response,
                             @NotNull Object handler){
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(token==null){
            return true;
        }

        String identity = jwtProvider.findIdentityByToken(token);
        MemberThreadLocal.set(identity);

        return true;
    }

    @Override
    public void postHandle(@NotNull HttpServletRequest request,
                           @NotNull HttpServletResponse response,
                           @NotNull Object handler,
                           ModelAndView modelAndView){

        if(MemberThreadLocal.get()==null){
            return;
        }

        MemberThreadLocal.remove();
    }
}

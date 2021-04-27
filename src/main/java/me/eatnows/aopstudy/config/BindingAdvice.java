package me.eatnows.aopstudy.config;

import me.eatnows.aopstudy.domain.ApiResponseDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component // 빈 객체의 메서드가 띄워진 후에 빈으로 띄워져야하기 때문에 @Configuration은 안된다.
// component는 controller가 띄워진 후에 띄워진다.
public class BindingAdvice {

    @Before("execution(* me.eatnows.aopstudy.web..*Controller.*(..))")
    public void testCheck() {
        System.out.println("로그를 남겼습니다.");

//        return "Dssdgsdg";
//        Before에서는 리턴값과 상관없이 실제 스택에 올라가있는 함수가 실행됨. 그냥 반환만 하는것 아무것도 없음 의미없음
    }

    @After("execution(* me.eatnows.aopstudy.web..*Controller.*(..))")
    public void testCheck2() {
        System.out.println("후처리 로그를 남겼습니다.");
    }

    // 함수의 앞 뒤를 제어하고 싶을때

    // 함수의 앞만 제어하고 싶을때 (username이 안들어왔으면 강제로 넣어주고 실행)

    // 함수의 뒤만 제어하고 싶을때 (응답만 관리하고 싶을떄)

    //@Before()
    //@After()
//    @Around("execution(* me.eatnows.aopstudy.web..*Controller.*(..))")
//    @Around("execution(public Integer me.eatnows.aopstudy.web..*Controller.*(..))")
//    @Around("execution(* me.eatnows.aopstudy.web..*Controller.save(..))")
    @Around("execution(* me.eatnows.aopstudy.web..*Controller.*(..))")
    // web 패키지안에 잇는 모든 것들중에 (컨트롤러로 끝나는) 안에 *모든것들 (메서드명) 의 인자 상관없음 모든것 (..)
    public Object validCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
        String method = proceedingJoinPoint.getSignature().getName();

        System.out.println("type : " + type);
        System.out.println("method : " + method);

        Object[] args = proceedingJoinPoint.getArgs();

        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult) arg;

                if (bindingResult.hasErrors()) {
                    Map<String, String> errorMap = new HashMap<>();

                    for (FieldError error : bindingResult.getFieldErrors()) {
                        errorMap.put(error.getField(), error.getDefaultMessage());
                    }

                    return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(), errorMap);
                }
            }
        }

        return proceedingJoinPoint.proceed(); // 스택에 올라간 함수를 실행해라
    }
}

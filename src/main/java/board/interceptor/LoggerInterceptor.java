package board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("deprecation")
@Slf4j
public class LoggerInterceptor implements HandlerInterceptor{
//	인터셉터는 HandlerInterceptor 클래스를 상속받아 구현
//	HandlerInterceptor 클래스는 preHandle(컨트롤러 수행 전 실행/ 데이터 입력받고 컨트롤러 동작 전 수행), postHandle(컨트롤러 수행 후 뷰로 보내기 전 수행/ 수행 다 하고 뷰로 보내기 전 한 번 더 확인하고 보내는 것), afterCompletion(뷰의 작업까지 완료된 후 수행) 메서드를 제공함
//	springboot 2.4, spring 5.3 부터 HandlerInterceptorAdapter 클래스가 제거됨. 
//	HandlerInterceptor 인터페이스를 사용하여 구현
	
	@Override
//	preHandle(컨트롤러 수행 전 실행/ 데이터 입력받고 컨트롤러 동작 전 수행)
//	-> 컨트롤러 열 때마다이니까, 세션 확인하면 됨. 세션이 살아 있으면 로그인 시켜주고, 아니면 로그아웃 시켜주는 방식으로 사용..
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.debug("========================== 시작 ==========================");
		log.debug("Request URI \t: " + request.getRequestURI());
		return true;
	}
	
	@Override
//	postHandle(컨트롤러 수행 후 뷰로 보내기 전 수행/ 수행 다 하고 뷰로 보내기 전 한 번 더 확인하고 보내는 것)
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) throws Exception {
		log.debug("========================== 종료 ==========================\n");
		
	}
}


































package board.configuration;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import board.interceptor.LoggerInterceptor;

// 추가 설정할 때 사용하는 어노테이션임
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

//	@Bean
////	Filter 는 import 중에서  javax.servlet 으로 import 하기
//	public Filter characterEncodingFilter() {
////		스프링부트 2.1 이후로는 기본적으로 UTF-8이 적용되어 있기 때문에 따로 설정할 필요 없음.
////		그러나 웹환경에 따라서 한글이 정상적으로 나오지 않으면 이 부분을 추가로 넣어주면 된다. 공식임.. 
////		CharacterEncodingFilter 클래스는 스프링에서 제공하는 클래스로 웹에서 주고받는 데이터의 헤더값을 UTF-8(지정한 문자형)로 인코딩함
//		CharacterEncodingFilter charEncodFilter = new CharacterEncodingFilter();
//		charEncodFilter.setEncoding("UTF-8");
////		HttpServletRequest, HttpServletResponse 모두 강제적으로 지정한 인코딩으로 변경
//		charEncodFilter.setForceEncoding(true);
//		
//		return charEncodFilter;
//	}
//	
////	@ResponseBody를 이용하여 결과를 출력 시 그 결과를 UTF-8로 인코딩한다는 뜻임.
//	@Bean
//	public HttpMessageConverter<String> responseBodyConverter() {
//		return new StringHttpMessageConverter(Charset.forName("UTF-8"));
//	}
	
//	이 부분 충돌나서 안 쓸것임... 
//	Board1Application.java에서 @SpringBootApplication(exclude= {MultipartAutoConfiguration.class}) 을 사용하여 중지된 자동 구성대신 사용하는 설정
	@Bean 
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		
//		파일의 인코딩을 UTF-8로 설정
		commonsMultipartResolver.setDefaultEncoding("UTF-8");
//		최대 업로드 파일 크기 설정
//		파일의 크기를 byte 단위로 설정이 가능함 (5 * 1024 * 1024 = 5mb)
//		컴퓨터는 2진수 계산을 하기 때문에 1000을 2의 10승으로 계산함 (그래서 우리가 생각하는 1000 -> 1024)
		commonsMultipartResolver.setMaxUploadSizePerFile(5 * 1024 * 1024);
		
		return commonsMultipartResolver;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoggerInterceptor());
	}
}



































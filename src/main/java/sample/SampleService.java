package sample;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;


@ToString
@Log4j2
@Service
@RequiredArgsConstructor
public class SampleService {
    // @Autowired를 이용하면 필요한 타입을 주입받을 수 있다는 사실 이용
    //@Autowired // 멤버 변수에 직접 @Autowired 선언하는 방식은 : 필드 주입 방식 -> root-context.xml 에 <bean class="sample.SampleDAO"></bean> 따위를 해줘야함

//    private final SampleDAO sampleDAO;

    @Qualifier("normal")
    private final SampleDAO sampleDAO;




    /*
        @Controller : MVC의 컨트롤러를 위한 어노테이션
        @Service : 서비스 계층의 객체를 위한 어노테이션
        @Repository : DAO와 같은 객체를 위한 어노테이션
        @Component : 일반 객체나 유틸리티 객체를 위한 어노테이션


     */



}

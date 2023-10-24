package controller;

import dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;


@Log4j2
@Controller
public class SampleController {

    @GetMapping("/hello") // 메서드 단위로 매핑 가능
    public void hello() {
        log.info("hello()...");
    }

    @GetMapping("/ex1")
    public void ex1(String name, int age) { // /ex1?name=inha&age=17 name, age 값 안적으면 오류
        log.info("ex1()...");
        log.info("name : " + name);
        log.info("age : " + age);
    }

    @GetMapping("/ex2") // /ex2 만 쳐도 됨 (defaultValue)
    public void ex2(@RequestParam(name = "name", defaultValue = "aaa2") String name,
                    @RequestParam(name = "age", defaultValue = "18") int age) {
        log.info("ex2()...");
        log.info("name : " + name);
        log.info("age : " + age);
    }

    @GetMapping("/ex3")
    public void ex3(LocalDate dueDate) { // /ex1?name=inha&age=17 name, age 값 안적으면 오류
        log.info("ex3()...");
        log.info("dueDate : " + dueDate);
    }

    @GetMapping("/ex4")
    public void ex4(Model model) { // model은 view로
        log.info("ex3()...");
        model.addAttribute("message", "Hello World");
    }

    @GetMapping("/ex4_1") // jsp에서는 별도의 처리없이 ${todoDTO} 사용가능
    public void ex4Ex(TodoDTO todoDTO, Model model) { // model은 view로
        log.info(todoDTO);
    }

    @GetMapping("/ex4_1_1") // 자동으로 생성된 변수면 todoDTO 외에 다른 이름 쓰고싶을때
    public void ex4Ex2(@ModelAttribute("dto") TodoDTO todoDTO, Model model) { // model은 view로
        log.info(todoDTO);
    }

    @GetMapping("/ex5") // ex5로 접근 -> 속성값이 전달됨 name, result
    public String ex5(RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("name", "ABC");
        redirectAttributes.addFlashAttribute("result", "success");

        return "redirect:/ex6"; // ex6 으로 이동!! -> ex5에 해당하는 jsp 파일은 필요하지 않으므로 exc6.jsp파일만 작성
//        <%-- redirectAttributes.addAttribute("name", "ABC"); 의 name은 주소창(쿼리스트링)에서 확인 ! (name 데이터가 쿼리스트링이 된 것을 볼수있음)
//        addFlashAttribute()로 추가한 데이터는 안보이지만 jsp에서는 사용가능--%>
    }

    @GetMapping("/ex6")
    public void ex6() {

    }

    @GetMapping("/ex7")
    public void ex7(String p1, int p2) { // 고의로 예외를 발생하는 코드
        log.info("p1 : " + p1);
        log.info("p2 : " + p2);
    }

    @GetMapping("/test01") // ex5로 접근 -> 속성값이 전달됨 name, result
    public void test01(TodoDTO todoDTO, Model model) {

//        return "redirect:/test02"; // ex6 으로 이동!! -> ex5에 해당하는 jsp 파일은 필요하지 않으므로 exc6.jsp파일만 작성
    }
    @PostMapping("/test02") // ex5로 접근 -> 속성값이 전달됨 name, result
    public void test02(TodoDTO todoDTO, Model model) {

    }

}

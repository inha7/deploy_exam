package controller;


import dto.PageRequestDTO;
import dto.PageResponseDTO;
import dto.TodoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.TodoService;

import javax.validation.Valid;


@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {
    // main controller

    // service 주입 -> @RequiredArgsConstructor
    private final TodoService todoService;

//    @RequestMapping("/list") // post,get 모두 포함
//    public void list() {
//        log.info("todo list");
//    }

    //    @RequestMapping(value = "/register", method = RequestMethod.GET)
//    public void register() {
//        log.info("todo register");
//    }
    @GetMapping("/register")
    public void register() {
        log.info("Get todo register");
    }

    @PostMapping("/register") // @Valid 있을시 유효성검사. bindingResult:결과값
    public String registerPost(@Valid TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) { // 변수명 통일바람
        log.info("Post todo register");
        if (bindingResult.hasErrors()) { // 유효성검사
            log.info("has error");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/todo/register";
        }
        log.info(todoDTO);

        todoService.register(todoDTO);

        return "redirect:/todo/list";
    }

//    @RequestMapping("/list")
//    public void list(Model model) {
//        log.info("todo list..");
//        model.addAttribute("dtoList", todoService.getAll()); // jsp 파일에서 사용할 이름은 dtoList
//    }

    @GetMapping("/list")
    public void list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {
        log.info(pageRequestDTO);

        if (bindingResult.hasErrors()) { // @Valid 를 이용해서 잘못된 파라미터 값들이 들어오면 page는 1, size는 10으로 고정된 값을 처리
            pageRequestDTO = PageRequestDTO.builder().build();
        }
        model.addAttribute("responseDTO", todoService.getList(pageRequestDTO)); // jsp 파일에서 responseDTO. 으로 받아옴
    }

    @GetMapping({"/read", "/modify"})
    public void read(Long tno, PageRequestDTO pageRequestDTO, Model model) { // PageRequestDTO pageRequestDTO : 페이지 정보주려고(상세에서 list 누르면 1페이지로만 이동되는것 수정.)
        TodoDTO todoDTO = todoService.getOne(tno); // 파라미터로 page, size 받음 - GetMapping?!
        log.info(todoDTO);

        model.addAttribute("dto", todoDTO); // dto라는 이름으로 jsp 파일에서 사용하게끔함.
    }

    @PostMapping("/remove")
    public String remove(Long tno,PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes) {
        log.info("-------remove-------");
        log.info("tno : " + tno);
        log.info("tno : " + pageRequestDTO);
        todoService.remove(tno);

//        redirectAttributes.addAttribute("page", 1); // 삭제시 1페이지로 이동 /todo/list?page=1&size=10
//        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "redirect:/todo/list?"+pageRequestDTO.getLink();
    }

    @PostMapping("/modify")
    public String modify(PageRequestDTO pageRequestDTO,
                         @Valid TodoDTO todoDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        log.info("----------modify----------");
        if (bindingResult.hasErrors()) {
            log.info("has error");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("tno", todoDTO.getTno());
            return "redirect:/todo/modify";
        }
        log.info(todoDTO);
        todoService.modify(todoDTO);

//        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
//        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        redirectAttributes.addAttribute("tno", todoDTO.getTno());
        return "redirect:/todo/read";
    }
}

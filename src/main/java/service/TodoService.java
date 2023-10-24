package service;

import dto.PageRequestDTO;
import dto.PageResponseDTO;
import dto.TodoDTO;

import java.util.List;

public interface TodoService {
    void register(TodoDTO todoDTO); // 선언 해놓고 TodoServiceImpl 클래스 에서 구현

//    List<TodoDTO> getAll(); // 선언 해놓고 TodoServiceImpl 클래스 에서 구현. 테스트용(실제로 사용안함)

//    PageResponseDTO<TodoDTO> getList(PageResponseDTO pageResponseDTO);

    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);

    TodoDTO getOne(Long tno);

    void remove(Long tno);

    void modify(TodoDTO todoDTO);


}

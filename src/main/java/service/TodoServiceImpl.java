package service;

import domain.TodoVO;
import dto.PageRequestDTO;
import dto.PageResponseDTO;
import dto.TodoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mapper.TodoMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service // Spring에서 Bean으로 사용한다! -> root-context.xml에 <context:component-scan base-package="service"/> 추가
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService { // TodoService를 구현
    // TodoService를 구현함 -> 스프링 빈으로 처리
    private final TodoMapper todoMapper;
    // 의존성 주입이 필요한 객체의 타입을 final로 고정 -> @ReqiredArgConstructor로 생성자 생성하는 방식 사용
    private final ModelMapper modelMapper;

    @Override
    public void register(TodoDTO todoDTO) {
        log.info(todoDTO);
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        log.info(todoVO);

        todoMapper.insert(todoVO);
    }

    @Override
    public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO) {
        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);
        List<TodoDTO> dtoList = new ArrayList<>();
        for (TodoVO todoVO : voList) {
            dtoList.add(modelMapper.map(todoVO, TodoDTO.class));
        }
        int total = todoMapper.getCount(pageRequestDTO);

        PageResponseDTO<TodoDTO> pageResponseDTO = PageResponseDTO.<TodoDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
        return pageResponseDTO;
    }

//    @Override
//    public List<TodoDTO> getAll() {
//        List<TodoVO> voList = todoMapper.selectAll();
//        List<TodoDTO> dtoList = new ArrayList<>();
//
//        for (TodoVO todoVO : voList) {
//            TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);
//            dtoList.add(todoDTO);
//        }
//        return dtoList;
//    }

    @Override
    public TodoDTO getOne(Long tno) {
        TodoVO todoVO = todoMapper.selectOne(tno);
        TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);

        return todoDTO;
//        return modelMapper.map(todoVO, TodoDTO.class);
    }

    @Override
    public void remove(Long tno) {
        todoMapper.delete(tno);
    }

    @Override
    public void modify(TodoDTO todoDTO) {
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);// todomapper의 update의 매개변수는 todoVo이므로 변환해야함
        todoMapper.update(todoVO);
    }

//    @Override
//    public List<TodoDTO> getAll() { // stream 이용
//        List<TodoDTO> dtoList = todoMapper.selectAll().stream()
//                .map(vo -> modelMapper.map(vo, TodoDTO.class))
//                .collect(Collectors.toList());
//        return dtoList;
//    }

}

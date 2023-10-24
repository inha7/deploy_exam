package service;

import dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import mapper.TodoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;


@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoServiceTests {

    @Autowired(required = false)
    private TodoService todoService;

    @Autowired(required = false) //
    private TodoMapper todoMapper;


    @Test
    public void testRegister() {
        TodoDTO todoDTO = TodoDTO.builder()
                .title("2222222222")
                .dueDate(LocalDate.of(2002, 06, 06)) // LocalDate.now()
                .writer("injik2")
                .build();

        todoService.register(todoDTO);

    }

//    @Test
//    public void testGetAll() {
//        List<TodoVO> todoDTO = todoMapper.selectAll();
//        List<TodoDTO> dtoList = new ArrayList<>();
//
//
//        log.info(dtoList);
//    }

    @Test
    public void testRemove() {
        Long tno = 5L;
        todoService.remove(tno);
    }

    @Test
    public void testModify() {
        TodoDTO todoDTO = TodoDTO.builder()
                .tno(2L)
                .title("2222222222")
                .dueDate(LocalDate.of(2002, 06, 06)) // LocalDate.now()
                .finished(false)
                .build();

        todoService.modify(todoDTO);
    }

    @Test
    public void testGetList() {

    }










}

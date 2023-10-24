package mapper;


import domain.TodoVO;
import dto.PageRequestDTO;
import dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoMapperTests {

    @Autowired(required = false) //
    private TodoMapper todoMapper;

    @Test
    public void testGetTime() {
        log.info(todoMapper.getTime());
    }

    @Test
    public void testInsert() {
        TodoVO todoVO = TodoVO.builder()
                .title("inj")
                .dueDate(LocalDate.of(2003, 06, 06))
                .writer("injik")
                .finished(true)
                .build();

        todoMapper.insert(todoVO);
    }

    @Test
    public void testSelectAll() {
        List<TodoVO> voList = todoMapper.selectAll();

//        voList.forEach(log::info);
//
//        voList.forEach(vo->log.info(vo));

        for (TodoVO todoVO : voList) {
            log.info(todoVO);
        }
    }

    @Test
    public void testSelectOne() {
        Long tno = 1L;
        TodoVO todoVO = todoMapper.selectOne(tno);
        log.info(todoVO);
    }

    @Test
    public void testDelete() {
        Long tno = 3L;
        log.info(todoMapper.selectOne(tno));
        todoMapper.delete(tno);
        log.info(todoMapper.selectOne(tno));
//        testSelectAll();
//        log.info(tno);
    }

    @Test
    public void testUpdate() {
        Long tno = 3580L;
        TodoVO todoVO = TodoVO.builder()
                .tno(tno)
                .title("in")
                .dueDate(LocalDate.of(2002, 06, 06))
                .writer("injik")
                .finished(true)
                .build();

        todoMapper.update(todoVO);
        log.info(todoMapper.selectOne(tno));
    }

    @Test
    public void testSelectList() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(11).build();
        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);
        voList.forEach(vo->log.info(vo));

//        voList.forEach(log::info);
//
//        voList.forEach(vo->log.info(vo));

//        for (TodoVO todoVO : voList) {
//            log.info(todoVO);
//        }
    }

    @Test
    public void testGetCount() {
//        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
//                .page(10).build();
        int cnt = todoMapper.getCount(PageRequestDTO.builder().build());
        log.info(cnt);
    }

    @Test
    public void testSelectSearch() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .types(new String[]{"t","w"}) // title, writer
                .keyword("in")
                .finished(false)
                .from(LocalDate.parse("2001-12-12"))
                .to(LocalDate.of(2002,12,12))
                .build();
        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);
        voList.forEach(vo -> log.info(vo));

        log.info(todoMapper.getCount(pageRequestDTO));

    }

}

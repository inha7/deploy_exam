package mapper;

import domain.TodoVO;
import dto.PageRequestDTO;

import java.util.List;

public interface TodoMapper {
    String getTime();

    void insert(TodoVO todoVO); // 메서드의 선언부만작성 -> TodoMapper.xml 에서 작업

    List<TodoVO> selectAll(); // test 단계에서 사용


    // 조회 기능 - "/todo/read?tno=xx" 와 같이 TodoController를 호출 하도록 개발
    TodoVO selectOne(Long tno); // Long 타입의 tno를 파라미터로 받고 TodoVO객체를 반환

    void delete(Long tno);

    void update(TodoVO todoVO); // 수정.

    List<TodoVO> selectList(PageRequestDTO pageRequestDTO);

    int getCount(PageRequestDTO pageRequestDTO);

}

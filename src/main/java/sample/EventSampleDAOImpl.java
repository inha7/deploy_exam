package sample;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
//@Primary //
@Qualifier("event")
public class EventSampleDAOImpl implements SampleDAO {
    // 두 클래스 중 하나를 @Primary라는 어노테이션 지정

}

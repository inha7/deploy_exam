package sample;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@ExtendWith(SpringExtension.class) // Junit5 버전에서 'spring-test'를 이욯
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
class SampleTests {
    @Autowired // 의존성 주입 : 해당 타입의 Bean이 존재하면 여기에 주입해 주기를 원한다.
    private SampleService sampleService; //

    @Autowired
    private DataSource dataSource;

    @Test
    public void testService() { // SampleService 테스트
        log.info(sampleService);
        Assertions.assertNotNull(sampleService);
    }

    @Test
    public void testConnection() throws Exception {
        Connection connection = dataSource.getConnection();
        log.info(connection);
        Assertions.assertNotNull(connection);

        connection.close();
    }



}
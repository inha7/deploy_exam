package controller.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.lang.reflect.Array;
import java.util.Arrays;

@ControllerAdvice
@Log4j2
public class CommonExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(NumberFormatException.class)
    public String exceptNumber(NumberFormatException numberFormatException) {
        log.error("exceptNumber...");
        log.error(numberFormatException.getMessage());

        return "NUMBER FORMAT EXCEPTION"; // 예외 발생시 화면에 뜨는 문자열
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String exceptCommon(Exception exception) {
        log.error("exception...");
        log.error(exception.getMessage());

        StringBuffer buffer = new StringBuffer("<ul>");
        buffer.append("<li>" + exception.getMessage() + "</li>");

        Arrays.stream(exception.getStackTrace()).forEach(stackTraceElement -> {
            buffer.append("<li>" + stackTraceElement + "</li>");
        });

        buffer.append("</ul>");
        return buffer.toString(); // 예외 발생시 화면에 뜨는 문자열
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound() {
        // 위 두 메서드는 @ResponseBody 이지만 이 메서드는 @ResponseStatus
        // -> return되는 문자열(여기는 custom404)의 이름으로 된 jsp 파일을 찾는다. 이후 web.xml의 DispatcherServlet 설정을 수정
        return "custom404";
    }
}

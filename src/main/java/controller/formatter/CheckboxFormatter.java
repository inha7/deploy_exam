package controller.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class CheckboxFormatter implements Formatter<Boolean> {
    // finished 여부를 체크박스로 하기때문에 작성
    @Override
    public Boolean parse(String text, Locale locale) throws ParseException {
        if (text == null) {
            return false;
        }
        return text.equals("on");
    }

    @Override
    public String print(Boolean object, Locale locale) {
        return object.toString();
    }

}

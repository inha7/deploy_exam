package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.index.qual.Positive;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO { // 페이지 이동 정보 - Model로 자동 전달
    @Builder.Default
    @Min(value=1)
    @Positive // 양수
    private int page=1;
// Min, Max로 외부 조작 대비
    @Builder.Default
    @Min(value = 10) // 페이지당 게시물 최소값
    @Max(value = 100)
    @Positive
    private int size = 10; // sql limit 뒤 숫자. 페이지당 게시물 개수!

    private String link;
    // 검색 기능을 위한 추가
    private String[] types; //
    private String keyword; // 제목,작성자 검색에 사용하는 문자열
    private boolean finished; // 완료여부
    private LocalDate from; // 특정 기간 검색을 위한 변수
    private LocalDate to; // 특정 기간 검색을 위한 변수
    public int getSkip() { // limit에서 사용하는 건너뛰기skip의 수
        return (page - 1) * size;
    }

    public String getLink() {
        StringBuilder builder = new StringBuilder();
        builder.append("page=" + this.page);
        builder.append("&size=" + this.size);
//        if (link == null) {
//
//        }
        if (this.finished) {
            builder.append("&finished=on");
        }
        if (this.types != null && this.types.length > 0) {
            for (int i = 0; i < this.types.length; i++) {
                builder.append("&types=" + types[i]);
            }
        }
        if (this.keyword != null) {
            try {
                builder.append("&keyword=" + URLEncoder.encode(keyword, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        if (this.from != null) {
            builder.append("&from=" + from.toString());
        }
        if (this.to != null) {
            builder.append("&to=" + to.toString());
        }

        link = builder.toString();
        return link;
    }


    // 화면에 검색조건 표시
    public boolean checkType(String type) {
        if (this.types == null || this.types.length == 0) {
            return false;
        } else {
            return Arrays.asList(this.types).contains(type);
        }
    }





}

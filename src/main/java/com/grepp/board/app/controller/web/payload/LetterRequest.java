package com.grepp.board.app.controller.web.payload;

import com.grepp.board.infra.emuns.Category;
import com.grepp.board.infra.entity.Home;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Text;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LetterRequest {
    private Long homeId;
    private String title;
    private Category category;
    private String story;

    // 글쓰기 시 요청되는 정보 들
    // -> 일단 만약 qr정보 안넣고 싶을 수 도 있으니까 nullable false로 안넣어도 되게끔
    // -> 그리고 추가하다보니 추가함에 따라서 letter 엔티티에 저장되어야하는 컬럼 추가해야 겠군
    // 화면에서 고정으로 보여줄때는 이미 url이 있어서 엔티티 필요없었는데 말이지
    private String qrUrl;
    private Integer size;
    private String qrName;  // 사실 이건 왜 넣는건지 모르겠음 ㅋ

}

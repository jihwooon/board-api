package jpa.imform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberSimpleDto {

  private String name;
  private String email;

  public class MemberSimpleDtoV1 {
  }
  /**
   * API 호출시 아래 속성도 같이 출력 됩니다.
   * 의도 되지 않은 속성은 만들지 않습니다.
   */
//  private Long boardId;
//  private List<BoardSimpleDto> boardSimpleDtos;

  /**
   * 별도의 Inner 클래스를 생성하면 Caused by : Unable to locate class 발생합니다.
   */
  @Getter
  public class MemberSimpleDtoV2 {
    private String name;
    private String phone;
    private String email;

    public MemberSimpleDtoV2(String name, String phone, String email) {
      this.name = name;
      this.phone = phone;
      this.email = email;
    }
  }

}

package techsuppDev.techsupp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @Column(name = "userid") // db의 id userid와 매핑
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 소셜여부 구분

    @Column(nullable = false)
    private String userName;

    @Column(unique = true, nullable = false) // 유일한 값만 저장 가능. 중복 저장 불가
    private String email;

    private String password;

    @Column(nullable = false)
    private String userPhonenum;

}

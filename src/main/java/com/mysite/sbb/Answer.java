package com.mysite.sbb;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne; // ManyToOne import 추가
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    // --- 이 부분이 핵심 수정 사항입니다. ---
    
    // 1. 답변은 하나의 질문에 속하므로 @ManyToOne을 사용합니다.
    @ManyToOne 
    // 2. Question 엔티티 객체를 필드로 추가합니다.
    private Question question; 
    
    // 3. Question 클래스를 참조하므로 import 구문을 추가해야 합니다.
    // (패키지가 같으므로 import가 필요 없지만, 습관적으로 추가해도 무방합니다. 
    // 만약 Question이 다른 패키지에 있다면 import가 필수입니다.)
}
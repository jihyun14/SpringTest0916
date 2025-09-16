package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Delicious {

	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false)
	    private String source; // 기억의 출처

	    private String taste; // 맛에 대한 묘사

	    private int rating; // 별점 (1~5)

		public Delicious(String source, String taste, int rating) {
		
			this.source = source;
			this.taste = taste;
			this.rating = rating;
		}
		//수정 이후 변경된 데이터
		public void update(String taste2, String source2, int rating2) {
			
			
		}
	    
//service에 dto에 엔티티 담아오려면 기본 엔티티에 생성자 생성 필요.
//		public void saveDelicious(DeliciousDto deliciousDto) {
//			Delicious delicious = new Delicious(deliciousDto.getTaste(),deliciousDto.getSource(),deliciousDto.getRating());
//			
//			this.deliciousRepository.save(delicious);
	    
	    
	
}

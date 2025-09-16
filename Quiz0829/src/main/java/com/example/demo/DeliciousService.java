package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class DeliciousService {

	private final DeliciousRepository deliciousRepository;
	
	
	public void saveDelicious(DeliciousDto deliciousDto) {
		Delicious delicious = new Delicious(deliciousDto.getTaste(),deliciousDto.getSource(),deliciousDto.getRating());
		
		this.deliciousRepository.save(delicious);
		
	}

//작성한 글 목록으로 만들기 위해 
	public List<Delicious> getList() {
		// TODO Auto-generated method stub
		//작성한 모든 글(엔티티에 있는 모든 row)를 불러오는 것.
		return this.deliciousRepository.findAll();
	}

	//수정을 위해 각 게시글(id)을 불러오기 위한 로직
	
	public Delicious getDelicious(Long id) {
		
		Optional<Delicious> optionalDelicious = this.deliciousRepository.findById(id);
		
		if(optionalDelicious.isPresent()) {
			return optionalDelicious.get();
			
		}else {
			throw new IllegalArgumentException("해당 게시글이 없습니다.");
		}
		
		
		
	}
	//update 사용해서 수정완료 버튼 누르면 수정이 완료되는 로직
	@Transactional
	public void update(Long id, Delicious dto) {
		Optional<Delicious> delicious = this.deliciousRepository.findById(id);
		Delicious d1 = delicious.get();
		d1.update(dto.getTaste(),dto.getSource(),dto.getRating());
		this.deliciousRepository.save(d1);
		
		
	}
	@Transactional
	public void delete(Long id) {
		Optional<Delicious> delicious = this.deliciousRepository.findById(id);
		Delicious d1 = delicious.get();
		this.deliciousRepository.delete(d1);
		
	}
	
	
}

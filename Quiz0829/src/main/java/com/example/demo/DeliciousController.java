package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class DeliciousController {

	private final DeliciousService deliciousService;
	
//	//index 보여주기 <작성한 글 목록으로 불러오는 로직짜기 전까지 유효, 목록으로 불러오는
	//GetMapping을 한 이후에는 주석처리하거나 삭제, 오류남.
//	@GetMapping("/memories")
//	public String memories(){
//		
//		return "memories/list";
//	}
	
	//글 작성 페이지 보여주기
	@GetMapping("/memories/new")
	public String memorienew(Model model) {
	model.addAttribute("memory",new DeliciousDto());
		
		
		return "memories/new-form";
	}
	
	
	//사용자 작성내용 db로 보내는 로직 작성
	@PostMapping("/memories/new")
	public String createMemories(@Valid DeliciousDto deliciousDto ,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "memories/new-form";
		}
		this.deliciousService.saveDelicious(deliciousDto);
		
		return "redirect:/memories";
	}
	
	//작성한 글 목록으로 만드는 로직
	
	@GetMapping("/memories")
	public String memories(Model model) {
		
		List<Delicious> delicious = deliciousService.getList();
		// "memories"(보낼 곳_list.html의 object),(delicious,보낼데이터_목록(getlist가 담긴 변수명))
		model.addAttribute("memories",delicious);
		
		return "memories/list"; 
	}
	
	//수정하기 (_ 기본적으로 작성했던 게시글 누르면 작성했던 게시글 그대로 불러오기)
	@GetMapping("/memories/{id}/edit")
	public String edit(@PathVariable("id")Long id, Model model) {
		Delicious delicious = this.deliciousService.getDelicious(id);
		model.addAttribute("memory",delicious);
		
		return "memories/edit-form";
	}
	
	//수정하기_게시글 내용 바꾼거 새롭게 저장해서 보여주기
	@PostMapping(value="/memories/{id}/edit")
	public String updateMemories(@PathVariable("id")Long id, Delicious dto) {
	
		this.deliciousService.update(id, dto);
		return "redirect:/memories";
		
	}
	
	//작성글 삭제
	@PostMapping(value="/memories/{id}/delete")
	public String deleteMemories(@PathVariable("id")Long id) {
		
		this.deliciousService.delete(id);
		return "redirect:/memories";
	}
	
	
	
}

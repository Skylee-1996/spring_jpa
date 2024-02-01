package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	private final BoardRepository repository;

	@Override
	public Long register(BoardDTO bdto) {
		//저장 객체는 Board
		//save insert 후 저장 객체를 리턴
		
		Long bno = repository.save(convertDtoToEntity(bdto)).getBno();
		return bno;
	}

	@Override
	public Page<BoardDTO> getList(int page) {
		//DB에서 list로 리턴이 되기 때문에 board객체의 list로 리턴 => Dto 객체로 변환
		//Sort.by(Sort.Direction.DESC, "기준칼럼명")
		// 1=> limit 0 ,10 / 2=> 10, 01 / 3-> 20,10
		//pageNo = 0부터 시작
		Pageable pageable = PageRequest.of(page,10, Sort.by("bno").descending());//페이징
		
		
		Page<Board> list = repository.findAll(pageable);
		
		Page<BoardDTO> dtoList = list.map(b-> convertEntityToDto(b));
				
		return dtoList;
	}

	@Override
	public BoardDTO getDetail(Long bno) {
	      //findById: id(기본키)를 주고 해당하는 객체를 리턴
	      //findById의 리턴타입 Optional<Board> 타입으로 리턴
	      //exception 방지를 위해 바로 객체에 넣는게 아니라 optional를 통해 받음
	      //Optional<T>: nullPointException가 발생하지 않도록 도와줌
	      //null이 오더라도 exception이 발생하지 않음!
	      //optional.isEmpty(): null인 경우 확인가능 (true, false로 리턴)
	      //optional.isPresent: 값이 있는지 확인 (true, false로 리턴)
	      //option.get(): 값을 가져오기

		
		Optional<Board> option = repository.findById(bno);
		return option.isPresent() ? convertEntityToDto(option.get()) : null;
	}

	@Override
	public Long modify(BoardDTO bdto) {
		//update: jpa는 update가 없음. 메서드가 없음..
		//기존의 객체를 가져와서 변경한 후 다시 저장
		Optional<Board> option = repository.findById(bdto.getBno());
		
		if(option.isPresent()) {
			Board entity = option.get();
			//변경 내용 set
			entity.setTitle(bdto.getTitle());
			entity.setContent(bdto.getContent());
			//다시저장
			return repository.save(entity).getBno();
		}
		
		return null;
	}

	@Override
	public void remove(Long bno) {
		repository.deleteById(bno);
	}

}

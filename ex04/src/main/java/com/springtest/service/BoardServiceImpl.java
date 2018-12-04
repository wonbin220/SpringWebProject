package com.springtest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.springtest.domain.BoardVO;
import com.springtest.domain.Criteria;
import com.springtest.domain.SearchCriteria;
import com.springtest.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO dao;

	@Transactional  // 첨부파일의 기능이 추가되면서 게미술의 등록은 tbl_board 테이블에 게시물 정보에 대한 insert작업과,
		@Override	// tbl_attach 테이블에 첨부파일의 이름이 함께 등록되는 작업이 진행되야함. BoardDAO의 create()와 addAttach()를 연속으로 사용하기때문에 트랜잭션처리
		public void regist(BoardVO board) throws Exception {
			dao.create(board);
			
			String[] files = board.getFiles();
			
			if(files == null) {
				return;
			}
			
			for (String fileName : files) {
				dao.addAttach(fileName);
			}
		}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)//트랜잭션 격리 , 대부분의 데이터베이스가 
		@Override									// 기본으로 사용하는 수준으로 다른 연결이 커밋하지 않은 데이터는 보지 못함
		public BoardVO read(Integer bno) throws Exception {
		
			dao.updateViewCnt(bno);
			return dao.read(bno);
		}

	@Transactional //첨부파일이 존재하는 경우 게시물의 수정은 '원래의 게시물 수정' +'기존 첨부파일 목록 삭제' + '새로운 첨부파일 정보 입력'
		@Override	//의 세가지 작업이 함께이루어져야해서 트랜잭션처리
		public void modify(BoardVO board) throws Exception {
			dao.update(board);	
			
			Integer bno = board.getBno();
			
			dao.deleteAttach(bno);
			
			String[] files = board.getFiles();
			
			if(files == null) {
				return;
			}
			
			for(String fileName : files) {
				dao.replaceAttach(fileName, bno);
			}
		}
	@Transactional	// tbl_attach가 tbl_board를 참조하기 때문에 반드시 첨부파일과 관련된 정보부터 삭제하고 게시글을 삭제
		@Override
		public void remove(Integer bno) throws Exception {
			dao.deleteAttach(bno);
			dao.delete(bno);
		}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return dao.listCriteria(cri);
	}

	@Override //전체 게시물의 숫자를 계산
	public int listCountCriteria(Criteria cri) throws Exception {
		return dao.countPaging(cri);
	}

	@Override
	public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception {

		return dao.listSearch(cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		
		return dao.listSearchCount(cri);
	}

	@Override
	public List<String> getAttach(Integer bno) throws Exception {
		
		return dao.getAttach(bno);
	}
	

}

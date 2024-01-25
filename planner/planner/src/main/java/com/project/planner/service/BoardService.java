package com.project.planner.service;

import com.project.planner.entity.Board;
import com.project.planner.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service    // SpringBoot
public class BoardService {

    @Autowired  // SpringBoot
    private BoardRepository boardRepository;
/*

    // 글 작성
    public void write(Board board) {

        boardRepository.save(board);
    }
*/

    // 글 작성 + 파일
    public void write(Board board, MultipartFile file) throws Exception {

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";  // 저장할 경로

        UUID uuid = UUID.randomUUID();  // 랜덤 식별자 id 생성

        String fileName = uuid + "_" + file.getOriginalFilename();  // 파일 이름 = 랜덤id _ 원래 파일 이름

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);  //  파일에 파일 경로 + 파일 이름을 전송

        board.setFilename(fileName);
        board.setFilepath("/files/" + fileName);

        boardRepository.save(board);
    }


    // 게시글 리스트
/*
    public List<Board> boardList() {

        return boardRepository.findAll();
    }
*/
    public Page<Board> boardList(Pageable pageable) {

        return boardRepository.findAll(pageable);
    }

    // 특정 게시글 불러오기
    public Board boardView(Integer id) {

        return boardRepository.findById(id).get();
    }

    // 글 삭제
    public void boardDelete(Integer id) {

        boardRepository.deleteById(id);
    }

    // 키워드 검색
    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable) {

        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }
}

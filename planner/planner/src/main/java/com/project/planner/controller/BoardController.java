package com.project.planner.controller;

import com.project.planner.entity.Board;
import com.project.planner.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller // Lombok
public class BoardController {

    @Autowired  // SpringBoot
    private BoardService boardService;

    @GetMapping("/move/board/write") // SpringBoot
    public String boardWriteForm() {

        return "./board/boardwrite";
    }

    @PostMapping("/board/write") // SpringBoot
    public String boardWrite(Board board, MultipartFile file) throws Exception {

//        boardService.write(board);
        boardService.write(board, file);

        return "redirect:/move/board/list";
    }

    @GetMapping("/move/board/list") // SpringBoot
    public String boardList(Model model, @PageableDefault(page = 0, size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, String searchKeyword) {    // SpringBoot   // 시작 페이지 = 1페이지, 사이즈가 2개, 정렬기준 = id, 정렬 = 내림차순


        // 키워드 검색
        Page<Board> list = null;

        if (searchKeyword == null) {
            list = boardService.boardList(pageable);
        } else {
            list = boardService.boardSearchList(searchKeyword, pageable);
        }

        // 페이징 처리
        int nowPage = list.getPageable().getPageNumber() + 1;   // 페이저블의 시작은 0이기 때문에 1을 더한다
        int startPage = Math.max(nowPage - 4, 1);   // 시작페이지 보다 -4 만큼 보여준다, 시작이 1보다 작으면 1부터 보여준다
        int endPage = Math.min(nowPage + 5, list.getTotalPages());  // 끝페이지 보다 +5 만큼 보여준다, 끝이 List보다 크면 List의 토탈 만큼 보여준다.

        model.addAttribute("list", list);
        model.addAttribute("nowPate", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "./board/boardlist";
    }

    @GetMapping("/move/board/view") // SpringBoot
    public String boardView(Model model, Integer id) {

        model.addAttribute("board", boardService.boardView(id));

        return "./board/boardview";
    }

    @GetMapping("/board/delete") // SpringBoot
    public String boardDelete(Integer id) {

        boardService.boardDelete(id);

        return "redirect:/move/board/list";
    }

    @GetMapping("/move/board/modify/{id}") // SpringBoot
    public String boardModify(@PathVariable("id") Integer id, Model model) { // SpringBoot

        model.addAttribute("board", boardService.boardView(id));

        return "./board/boardmodify";
    }

    @PostMapping("/board/update/{id}") // SpringBoot
    public String boardUpdate(@PathVariable("id") Integer id, Board board, MultipartFile file) throws Exception { // SpringBoot

        System.out.println(board.getId());
        System.out.println(board.getTitle());
        System.out.println(board.getContent());

        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

//        boardService.write(boardTemp);
        boardService.write(boardTemp, file);

        return "redirect:/move/board/list";
    }
}

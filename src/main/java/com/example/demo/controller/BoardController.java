package com.example.demo.controller;

import com.example.demo.dto.BoardDto;
import com.example.demo.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BoardController {
    private BoardService boardService;

    /* 게시글 목록 */
    @GetMapping("/board")
    public ResponseEntity<List<BoardDto>> list() {
        List<BoardDto> boardList = boardService.getBoardlist();
        return new ResponseEntity<>(boardList, HttpStatus.OK);
    }


    @PostMapping("/board")  // 201
    public ResponseEntity<Long> write(@RequestBody BoardDto boardDto) { // 글 쓰기
        Long id = boardService.savePost(boardDto);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping("/board/{no}")
    public ResponseEntity<Long> update(@PathVariable("no") Long no, @RequestBody BoardDto boardDto) {
        Long id = boardService.updatePost(no, boardDto);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @DeleteMapping("/board/{no}")
    public ResponseEntity<Void> delete(@PathVariable("no") Long no) {
        boardService.deletePost(no);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


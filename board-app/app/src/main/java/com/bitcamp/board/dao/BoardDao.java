package com.bitcamp.board.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import com.bitcamp.board.domain.Board;

// 게시글 목록을 관리하는 역할
//
public class BoardDao {

  List<Board> list = new LinkedList<>();
  private int boardNo = 0;
  String filename;

  public BoardDao(String filename) {
    this.filename = filename;
  }

  public void load() throws Exception {
    try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
      String str;
      while ((str = in.readLine()) != null) {

        Board board = Board.create(str);

        list.add(board);
        boardNo = board.no;
      }
    }
  }

  public void save() throws Exception {
    try (FileWriter out = new FileWriter(filename)) {
      for (Board board : list) {
        out.write(board.toCsv() + "\n");
      }
    }
  }

  public void insert(Board board) {
    board.no = nextNo();
    list.add(board);
  }

  public Board findByNo(int boardNo) {
    for (int i = 0; i < list.size(); i++) {
      Board board = list.get(i);
      if (board.no == boardNo) {
        return board;
      }
    }
    return null;
  }

  public boolean delete(int boardNo) {
    for (int i = 0; i < list.size(); i++) {
      Board board = list.get(i);
      if (board.no == boardNo) {
        return list.remove(i) != null;
      }
    }
    return false;
  }

  public Board[] findAll() {

    // 목록에서 값을 꺼내는 일을 할 객체를 준비한다.
    Iterator<Board> iterator = list.iterator();

    // 역순으로 정렬하여 리턴한다.
    Board[] arr = new Board[list.size()];

    int index = list.size() - 1;
    while (iterator.hasNext()) {
      arr[index--] = iterator.next();
    }
    return arr;
  }

  private int nextNo() {
    return ++boardNo;
  }
}















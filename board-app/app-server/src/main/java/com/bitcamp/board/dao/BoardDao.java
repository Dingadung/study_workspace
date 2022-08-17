package com.bitcamp.board.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import com.bitcamp.board.domain.Board;
import com.google.gson.Gson;

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

      // 파일에서 JSON 문자열을 모두 읽어 StringBuilder에  담는다.
      StringBuilder stringBuilder = new StringBuilder();
      String str;
      while ((str = in.readLine()) != null) {
        stringBuilder.append(str);
      }

      // StringBuilde에  보관된 JSON 문자열을 가지고 Board[]을 생성한다./
      Board[] arr = new Gson().fromJson(stringBuilder.toString(), Board[].class);

      // Board[]배열의 저장된 객체를 List로 옮긴다.
      for(int i=0;i<arr.length;i++) {
        list.add(arr[i]);
      }

      // 게시글 데이터를 로딩한 후, 마지막 게시글 번호를 설정해 준다.
      // 새 게시물의 번호 설정
      boardNo = arr[arr.length-1].no; 
    }
  }

  public void save() throws Exception {
    try (FileWriter out = new FileWriter(filename)) {
      Board[] boards = list.toArray(new Board[0]); // 현재 list에 들어 있는 보드 객체들 받아오기
      //String json = new Gson().toJson(boards); // 이렇게 코딩 안하고, 그냥 바로 넣어버린다.
      out.write( new Gson().toJson(boards));
    }
  }

  public void insert(Board board) {
    board.no = nextNo();
    list.add(board);
  }

  public boolean update(Board board) {
    for (int i = 0; i < list.size(); i++) {
      Board b = list.get(i);
      if (b.no == board.no) {
        list.set(i, board);
        return true;
      }
    }
    return false;
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















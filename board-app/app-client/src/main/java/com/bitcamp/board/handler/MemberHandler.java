/*
 * 회원 메뉴 처리 클래스
 */
package com.bitcamp.board.handler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Date;
import com.bitcamp.board.domain.Member;
import com.bitcamp.handler.AbstractHandler;
import com.bitcamp.util.Prompt;
import com.google.gson.Gson;

public class MemberHandler extends AbstractHandler {

  String dataName;
  DataInputStream in;
  DataOutputStream out;

  public MemberHandler(String dataName, DataInputStream in, DataOutputStream out) {
    super(new String[] {"목록", "상세보기", "등록", "삭제", "변경"});

    this.dataName = dataName;
    this.in = in;
    this.out =out;
  }

  @Override
  public void service(int menuNo){
    try{
      switch (menuNo) {
        case 1: this.onList(); break;
        case 2: this.onDetail(); break;
        case 3: this.onInput(); break;
        case 4: this.onDelete(); break;
        case 5: this.onUpdate(); break;
      }
    }catch(Exception e) {
      throw new RuntimeException();
    }
  }

  private void onList() {
    try {
      out.writeUTF(dataName);
      out.writeUTF("findAll");
      if(in.readUTF().equals("fail")) {
        System.out.println("목록을 가져오는데 실패했습니다!");
        return;
      }
      String json = in.readUTF();
      Member[] members = new Gson().fromJson(json, Member[].class);

      System.out.println("이메일       이름");

      for (Member member : members) {
        System.out.printf("%s   %s\n",
            member.email, member.name);
      }
    }catch(Exception e) {
      throw new RuntimeException(e);
    } // try-catch

  }

  private void onDetail() {
    try {
      String email = Prompt.inputString("조회할 회원 이메일? ");
      out.writeUTF(dataName);
      out.writeUTF("findByEmail");
      out.writeUTF(email);

      if(in.readUTF().equals("fail")) {
        System.out.println("해당 이메일의 회원이 없습니다!");
        return;
      }

      String json = in.readUTF();
      Member member = new Gson().fromJson(json, Member.class);

      System.out.printf("이름: %s\n", member.name);
      System.out.printf("이메일: %s\n", member.email);
      Date date = new Date(member.createdDate);
      System.out.printf("등록일: %tY-%1$tm-%1$td %1$tH:%1$tM\n", date);

    }catch(Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void onInput() throws Exception{
    try {
      Member member = new Member();

      member.name = Prompt.inputString("이름? ");
      member.email = Prompt.inputString("이메일? ");
      member.password = Prompt.inputString("암호? ");
      member.createdDate = System.currentTimeMillis();

      out.writeUTF(dataName);
      out.writeUTF("insert");
      String json = new Gson().toJson(member);
      out.writeUTF(json); // json 서버로 보내기
      if(in.readUTF().equals("success")) {
        System.out.println("회원을 등록했습니다.");
      }else {
        System.out.println("회원 등록에 실패했습니다!");
      }
    }catch(Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void onDelete() throws Exception{
    try {
      String email = Prompt.inputString("삭제할 회원 이메일? ");
      out.writeUTF(dataName);
      out.writeUTF("delete");
      out.writeUTF(email);

      if(in.readUTF().equals("success")) {
        System.out.println("삭제하였습니다.");
      } else {
        System.out.println("해당 이메일의 회원이 없습니다!");
      }

    }catch(Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void onUpdate() throws Exception{
    try {
      String email = Prompt.inputString("변경할 회원 이메일? ");

      out.writeUTF(dataName);
      out.writeUTF("findByEmail");
      out.writeUTF(email);

      if(in.readUTF().equals("fail")) {
        System.out.println("해당 이메일의 회원이 없습니다!");
        return;
      }

      String json = in.readUTF();
      Member member = new Gson().fromJson(json, Member.class);

      member.name = Prompt.inputString("이름?(" + member.name + ") ");

      String input = Prompt.inputString("변경하시겠습니까?(y/n) ");
      if (input.equals("y")) {
        // 회원 변경하기
        out.writeUTF(dataName);
        out.writeUTF("update");
        out.writeUTF(new Gson().toJson(member));
        if(in.readUTF().equals("success")) {
          System.out.println("변경했습니다.");
        }else {
          System.out.println("변경 실패했습니다!");
        }

      } else {
        System.out.println("변경 취소했습니다.");
      }
    }catch(Exception e) {
      throw new RuntimeException(e);
    }
  }
}





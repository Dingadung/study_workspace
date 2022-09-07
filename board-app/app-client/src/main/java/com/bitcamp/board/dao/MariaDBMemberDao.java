package com.bitcamp.board.dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import com.bitcamp.board.domain.Member;
import com.google.gson.Gson;

public class MariaDBMemberDao {

  public boolean insert(Member email)throws Exception {
    try(

        ){
      out.writeUTF(dataName);
      out.writeUTF("insert");
      out.writeUTF(new Gson().toJson(email)); // json을 서버로 보내기
      // 서버로부터 요청했던 데이터 읽어오기
      return in.readUTF().equals("success");
    }
  }

  public boolean update(Member email) throws Exception{
    try(Socket socket =  new Socket(ip, port);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream())
        ){
      out.writeUTF(dataName);
      out.writeUTF("update");
      out.writeUTF(new Gson().toJson(email));
      return in.readUTF().equals("success");
    }
  }

  public Member findByEmail(String email)  throws Exception{
    try(Socket socket =  new Socket(ip, port);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream())
        ){
      out.writeUTF(dataName);
      out.writeUTF("findByEmail");
      out.writeUTF(email);
      if(in.readUTF().equals("fail")) {
        return null;
      }
      return  new Gson().fromJson( in.readUTF(), Member.class);
    }
  }

  public boolean delete(String email) throws Exception{
    try(Socket socket =  new Socket(ip, port);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream())
        ){
      out.writeUTF(dataName);
      out.writeUTF("delete");
      out.writeUTF(email);
      return in.readUTF().equals("success");
    }
  }

  public Member[] findAll() throws Exception{
    try(Socket socket =  new Socket(ip, port);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream())
        ){
      out.writeUTF(dataName);
      out.writeUTF("findAll");

      if(in.readUTF().equals("fail")) {
        return null;
      }

      return new Gson().fromJson( in.readUTF(), Member[].class);
    }
  }



}















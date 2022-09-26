package com.bitcamp.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bitcamp.board.dao.MemberDao;
import com.bitcamp.board.domain.Member;

@WebServlet("/member/add")
public class MemberAddController extends HttpServlet{

  private static final long serialVersionUID = 1L;

  MemberDao memberDao;

  @Override
  public void init() {
    memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      Member member = new Member();
      member.name = request.getParameter("name");
      member.email = request.getParameter("email");
      member.password = request.getParameter("password");

      if (memberDao.insert(member) == 0) {
        throw new Exception("멤버 등록 실패"); 
      }

      // Refresh
      response.setContentType("text/html;charset=UTF-8"); 
      request.getRequestDispatcher("/member/add.jsp").include(request, response); 

    } catch(Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
  }
}

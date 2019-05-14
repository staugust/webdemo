package cn.augusto.web.stb.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoteServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    System.out.println(req);
    // map multi-parts http url to single part
    System.out.println(req.getPathInfo());

    resp.getWriter().write("heheda~");
    //super.doGet(req, resp);
  }
}

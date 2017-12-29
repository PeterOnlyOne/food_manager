package cn.et.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.generic.NEW;

import cn.et.model.MyDesk;
import cn.et.model.PageTools;

/**
 * Servlet implementation class DeskServlet
 */
public class DeskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    MyDesk myDesk = new MyDesk();
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("dname");
		String curPage = request.getParameter("curPage");
		Integer curPageInt = 1;
		if (curPage != null) {
			curPageInt = Integer.parseInt(curPage);
		}
		PageTools tableList = myDesk.getTableListPage(name,curPageInt);
		request.setAttribute("tableList", tableList);
		request.getRequestDispatcher("/detail/boardList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package cn.et.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.et.model.MyCuisine;
import cn.et.model.PageTools;

/**
 * Servlet implementation class CuisineServlet
 */
public class CuisineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CuisineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    MyCuisine myCuisine = new MyCuisine();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("typeName");
		String curPage = request.getParameter("curPage");
		Integer curPageInt = 1;
		if (curPage != null) {
			curPageInt = Integer.parseInt(curPage);
		}
		PageTools cuisineList = myCuisine.getCuisineListPage(name,curPageInt);
		request.setAttribute("cuisineList", cuisineList);
		request.getRequestDispatcher("/detail/cuisineList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

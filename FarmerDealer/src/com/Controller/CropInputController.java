package com.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DatabaseDao.DatabaseHelper;

/**
 * Servlet implementation class CropInputController
 */
@WebServlet("/CropInputController")
public class CropInputController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CropInputController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String CropeName=request.getParameter("crop");
		String Quantity=request.getParameter("wgt");
		DatabaseHelper DbObj=new DatabaseHelper();
		HttpSession session=request.getSession();
		String username=(String)session.getAttribute("username");
		long farmid=DbObj.getFarmIdl(username);
		int cnt=DbObj.cropInput(farmid, CropeName, Quantity);
		if(cnt>0){
			response.sendRedirect("CropReg.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

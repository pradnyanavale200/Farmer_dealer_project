package com.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DatabaseDao.DatabaseHelper;
import com.Model.DisplayAllData;
import com.Model.DisplayWeather;

/**
 * Servlet implementation class CropWeatherController
 */
@WebServlet("/CropWeatherController")
public class CropWeatherController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CropWeatherController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String CropeName=request.getParameter("crop");
		String weather=request.getParameter("wea");
		DatabaseHelper DbObj=new DatabaseHelper();
		
		List<DisplayWeather> lst=DbObj.cropWeather(CropeName, weather);
		
		HttpSession session=request.getSession(true);
		session.setAttribute("weather",lst);
		response.sendRedirect("DisplayWeather.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

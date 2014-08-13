package com.pwc.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DataCombatServlet
 */
@WebServlet("/DataCombatServlet")
public class DataCombatServlet extends HttpServlet {
	private Map<String,String> eTagMap = new HashMap<String,String>();
	//sessionId used for session record
    //private String sessionId = null;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataCombatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		if (uri.endsWith("jsp")) {
			//if the session is valid,retrieve the data as per the seesionId
			String sessionId = (String)request.getSession().getAttribute("Etag");
			if(null != sessionId || "".equals(sessionId)){
				String newString = eTagMap.get(sessionId);
				request.setAttribute("newString", newString);
			}
			uri = "showdata.jsp";
		} else if (uri.endsWith("jpg")) {
			//check whether If-None_Match header info exists or not
			String etag = request.getHeader("If-None-Match");
			if (etag != null && !"".equals(etag)) {
				//System.out.println(etag);
				String newString = eTagMap.get(etag);
				if(newString != null){
					//System.out.println("get value from client: " + newString);
					request.setAttribute("newString", newString);
					//return;
				}
				else{
					request.setAttribute("newString", "");
					//return;
				}
			}
			uri = "resources/images/fingerprinting.jpg";
		}

		request.getRequestDispatcher(uri).forward(request, response);
		//if the current sessionId is null,retrieve Etag from the response
		request.getSession().setAttribute("Etag", response.getHeader("Etag"));
//		if(null == sessionId){
//			//sessionId = response.getHeader("Etag");
//			
//		}
//		else {
//			if (null == eTagMap.get(sessionId)|| eTagMap.get(sessionId).equals(""))
//				eTagMap.put(sessionId, "");
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String new_string = request.getParameter("newString");
		String etag = (String)request.getSession().getAttribute("Etag");
		//retrieve sessionId from previous GET 
		eTagMap.put(etag,new_string);
		//request.getSession().setAttribute("newSting", new_string);
		request.setAttribute("newString", new_string);
		request.getRequestDispatcher("/showdata.jsp").forward(request, response);
		
	}

}

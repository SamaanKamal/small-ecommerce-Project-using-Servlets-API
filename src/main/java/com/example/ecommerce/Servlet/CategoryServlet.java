package com.example.ecommerce.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.example.ecommerce.Model.Category;
import com.example.ecommerce.Service.Category.CategoryService;
import com.example.ecommerce.Service.Category.CategoryServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/categories/*")
public class CategoryServlet extends HttpServlet{
	
	private CategoryService categoryService;
	private ObjectMapper objectMapper;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		categoryService = new CategoryServiceImp();
		objectMapper = new ObjectMapper();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
		resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        String pathInfo = req.getPathInfo();
        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                // Get all categories part
                String json = objectMapper.writeValueAsString(categoryService.findAll());
                out.write(json);
            } else {
                // Get category by ID part
            	String[] pathParts = pathInfo.split("/");
            	if (pathParts.length < 2) {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid category ID");
                    return;
                }
                int id = Integer.parseInt(pathParts[1]);
                Category category = categoryService.find(id);
                if (category != null) {
                    String json = objectMapper.writeValueAsString(category);
                    out.write(json);
                } else {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Could not find any category with id : " + id);
                }
            }
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doDelete(req, resp);
		resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
		String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Category ID is required");
            return;
        }

        try {
        	String[] pathParts = pathInfo.split("/");
        	if (pathParts.length < 2) {
        		resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid category ID");
        		return;
        	}
            int id = Integer.parseInt(pathParts[1]);
            categoryService.remove(id);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
	}
	
	

}

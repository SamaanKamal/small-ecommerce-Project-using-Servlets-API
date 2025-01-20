package com.example.ecommerce.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.example.ecommerce.Model.Product;
import com.example.ecommerce.Service.Product.ProductService;
import com.example.ecommerce.Service.Product.ProductServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/products/*")
public class ProductServlet extends HttpServlet{
	
	private ProductService productService;
	private ObjectMapper objectMapper;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		productService = new ProductServiceImp();
		objectMapper = new ObjectMapper();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
		resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String pathInfo = req.getPathInfo();
        String categoryId = req.getParameter("categoryId");
        PrintWriter out = resp.getWriter();
        try {
            if (categoryId != null) {
                // Get products by category part
                String json = objectMapper.writeValueAsString(productService.findByCategory(Integer.parseInt(categoryId)));
                out.write(json);
            } else if (pathInfo == null || pathInfo.equals("/")) {
                // Get all products part
                String json = objectMapper.writeValueAsString(productService.findAll());
                out.write(json);
            } else {
                // Get product by ID part
            	String[] pathParts = pathInfo.split("/");
            	if (pathParts.length < 2) {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Product ID");
                    return;
                }
                int id = Integer.parseInt(pathParts[1]);
                Product product = productService.find(id);
                if (product != null) {
                    String json = objectMapper.writeValueAsString(product);
                    out.write(json);
                } else {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Could not find any Product with id : " + id);
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
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
        	String[] pathParts = pathInfo.split("/");
        	if (pathParts.length < 2) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Product ID");
                return;
            }
            int id = Integer.parseInt(pathParts[1]);
            productService.remove(id);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
	}

}

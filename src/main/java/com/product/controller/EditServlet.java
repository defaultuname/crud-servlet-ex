package com.product.controller;

import ch.qos.logback.classic.Logger;
import com.product.dao.SmartphoneDao;
import com.product.model.Smartphone;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

/*
    Сервлет, отвечающий за изменение существующего смартфона.
*/

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(EditServlet.class);
    private SmartphoneDao smartphoneDao;

    @Override
    public void init() {
        smartphoneDao = new SmartphoneDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Smartphone smartphone = smartphoneDao.getById(id);  // Получаем сущность, которую хотим изменить.

        logger.info("Getting for editing smartphone by id {}", id);
        req.setAttribute("smartphone", smartphone);  // Передаём её на JSP.
        getServletContext().getRequestDispatcher("/edit.jsp").forward(req, resp);
        logger.info("Forwarding to /edit.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String model = req.getParameter("model");
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        smartphoneDao.update(new Smartphone(id, model, price)); // Получаем изменённую сущность и записываем её в БД.

        logger.info("Updated. Smartphone with id {} now has model {} and price {}", id, model, price);
        resp.sendRedirect(req.getContextPath() + "/index");
        logger.info("Redirecting to /index page");
    }
}

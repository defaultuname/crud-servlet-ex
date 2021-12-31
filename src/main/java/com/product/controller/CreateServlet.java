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
    Сервлет, отвечающий за создание нового смартфона.
*/

@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(CreateServlet.class);
    private SmartphoneDao smartphoneDao;

    @Override
    public void init() {
        smartphoneDao = new SmartphoneDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/create.jsp").forward(req, resp); // При переходе по URL /create попадаем на create.jsp
        logger.info("Forwarding to /create.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String model = req.getParameter("model"); // Получаем, что было введено в форму.
            BigDecimal price = new BigDecimal(req.getParameter("price"));
            smartphoneDao.createSmartphone(new Smartphone(model, price));  // И создаём с этими данными сущность.

            logger.info("Creating new smartphone with price {} and model {} in DB", price, model);
            resp.sendRedirect(req.getContextPath() + "/index"); // После всего редиректимся на /index.
            logger.info("Redirecting to /index page");

        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + "/error");
            logger.error("Unexpected exception!", e);
        }
    }
}

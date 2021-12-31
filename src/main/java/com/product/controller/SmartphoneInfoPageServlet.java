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

/*
    Сервлет, отвечающий за вывод информации о конкретном смартфоне.
*/

@WebServlet("/info")
public class SmartphoneInfoPageServlet extends HttpServlet {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(SmartphoneInfoPageServlet.class);
    private SmartphoneDao smartphoneDao;

    @Override
    public void init() {
        smartphoneDao = new SmartphoneDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Smartphone smartphone = smartphoneDao.getById(id);      // Получаем конкретный смартфон

        req.setAttribute("oneSmartphone", smartphone);    // и передаём через оттрибут на JSP.
        logger.info("Getting for smartphone with id {} and setting to attribute \"oneSmartphone\"", id);
        getServletContext().getRequestDispatcher("/info.jsp").forward(req, resp);
        logger.info("Forwarding to /info page");
    }
}

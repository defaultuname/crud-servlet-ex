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
import java.util.List;

/*
    Сервлет, отвечающий за index page (главную страницу).
*/

@WebServlet(urlPatterns = {"/index", ""})
public class IndexPageServlet extends HttpServlet {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(IndexPageServlet.class);
    private SmartphoneDao smartphoneDao;

    @Override
    public void init() {
        smartphoneDao = new SmartphoneDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Smartphone> smartphoneList = smartphoneDao.getAll(); // На главной странице приложения отображён лист всех смартфонов. Получаем его
        req.setAttribute("smartphones", smartphoneList);    // и передаём через оттрибут на JSP.

        logger.info("Getting all smartphones from DB and setting to attribute \"smartphones\"");
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        logger.info("Forwarding to /index page");
    }
}

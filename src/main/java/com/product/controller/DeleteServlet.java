package com.product.controller;

import ch.qos.logback.classic.Logger;
import com.product.dao.SmartphoneDao;
import org.slf4j.LoggerFactory;
;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    Сервлет, отвечающий за удаление смартфона.
*/

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(DeleteServlet.class);
    private SmartphoneDao smartphoneDao;

    @Override
    public void init() {
        smartphoneDao = new SmartphoneDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            smartphoneDao.delete(id);

            logger.info("Smartphone with id {} has been deleted from DB", id);
            resp.sendRedirect(req.getContextPath() + "/index");
            logger.info("Redirecting to /index page");

        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + "/error");
            logger.error("Unexpected exception!", e);
        }
    }
}

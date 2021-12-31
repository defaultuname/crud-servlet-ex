package com.product.controller;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    Севрлет для логирования ошибок.
*/

@WebServlet("/error")
public class ErrorHandlerServlet extends HttpServlet {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(ErrorHandlerServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer code = (Integer) req.getAttribute("javax.servlet.error.status_code");
        getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        logger.error("Unexpected exception! Code: {}", code);
    }
}

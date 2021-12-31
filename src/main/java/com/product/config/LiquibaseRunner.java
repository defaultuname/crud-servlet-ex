package com.product.config;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.SQLException;

/*
    Listener, автоматически подргружающий Liquibase при запуске приложения.
    Создаёт в БД таблицу SMARTPHONE и заполняет её начальными записями.
*/

@WebListener
public class LiquibaseRunner implements ServletContextListener {
    private static final String liquibaseChangeLogFile = "liquibase/master-db.xml"; // Относиельный путь до master-файла Liquibase

    public void contextInitialized(ServletContextEvent event) {
        try (Connection connection = JdbcDataSource.getConnection()) {
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            try (Liquibase liquibase = new Liquibase(liquibaseChangeLogFile, new ClassLoaderResourceAccessor(), database)) {
                liquibase.update(new Contexts(), new LabelExpression());
            }
        } catch (SQLException | LiquibaseException e) {
            e.printStackTrace();
        }
    }
}
package com.product.dao;

import ch.qos.logback.classic.Logger;
import com.product.config.JdbcDataSource;
import com.product.model.Smartphone;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
Имплементация DAO-слоя. Ответственен за всю работу JDBC с БД.
*/

public class SmartphoneDao implements AbstractDao<Smartphone> {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(SmartphoneDao.class);

    @Override // Create-операция
    public void createSmartphone(Smartphone smartphone) {
        String query = "INSERT INTO smartphone (model, price) VALUES (?, ?)";
        try (Connection connection = JdbcDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            if (smartphone.getPrice().compareTo(BigDecimal.ZERO) > 0) { // Не даём создать сущность с отрицательной ценой
                statement.setString(1, smartphone.getModel());
                statement.setBigDecimal(2, smartphone.getPrice());
                statement.executeUpdate();
            } else {
                logger.info("Exception! Price < 0");
                throw new IllegalArgumentException();
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override //ReadAll-операция
    public List<Smartphone> getAll() {

        String query = "SELECT * FROM smartphone";
        List<Smartphone> listOfSmartphones = new ArrayList<>();

        try (Connection connection = JdbcDataSource.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(query);                    // Результат SQL-запроса присваиваем в ResultSet.
            while (resultSet.next()) {                                              // Проходимся по нему, маппим записи из БД в сущности из java-кода
                int prodId = resultSet.getInt("id");                     // и кладём их в лист.
                String model = resultSet.getString("model");
                BigDecimal price = resultSet.getBigDecimal("price");
                listOfSmartphones.add(new Smartphone(prodId, model, price));
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return listOfSmartphones;
    }

    @Override //Read-операция
    public Smartphone getById(int id) {

        String query = "SELECT * FROM smartphone WHERE id = ?";
        Smartphone smartphone = null;

        try (Connection connection = JdbcDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                int prodId = set.getInt("id");
                String model = set.getString("model");
                BigDecimal price = set.getBigDecimal("price");
                smartphone = new Smartphone(prodId, model, price);
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return smartphone;
    }

    @Override // Update-операция
    public void update(Smartphone smartphone) {

        String query = "UPDATE smartphone SET model = ?, price = ? WHERE id = ?";

        try (Connection connection = JdbcDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            if (smartphone.getPrice().compareTo(BigDecimal.ZERO) > 0) { // Не даём изменить цену на отрицательную
                statement.setInt(3, smartphone.getId());
                statement.setString(1, smartphone.getModel());
                statement.setBigDecimal(2, smartphone.getPrice());
                statement.executeUpdate();
            } else {
                logger.info("Exception! Price < 0");
                throw new IllegalArgumentException();
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override // Delete-операция
    public void delete(int id) {

        String query = "DELETE FROM smartphone WHERE id = ?";

        try (Connection connection = JdbcDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}

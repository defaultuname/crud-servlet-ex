package com.product.dao;

import com.product.config.LiquibaseRunner;
import com.product.model.Smartphone;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class SmartphoneDaoTest {
    private static SmartphoneDao smartphoneDao;
    private static Smartphone smartphoneTest;

    @BeforeAll
    static void init_db_connection() {
        new LiquibaseRunner().contextInitialized(null);
        smartphoneDao = new SmartphoneDao();
        smartphoneTest = new Smartphone("test", new BigDecimal(15));
    }

    @Test
    @Disabled("Тест для проверки количества стартовых записей в БД")
    void int_db_rows_equals() {
        assertEquals(smartphoneDao.getAll().size(), 5);
    }

    @Test
    void create_smartphone_test() {
        smartphoneDao.createSmartphone(smartphoneTest);
    }

    @Test
    void create_smart_with_price_less_than_0() {
        assertThrows(IllegalArgumentException.class, () -> smartphoneDao.createSmartphone(new Smartphone("Test", new BigDecimal(-1))));
    }

    @Test
    void get_smart_by_id_less_than_0() {
        assertNull(smartphoneDao.getById(-1));
    }

    @Test
    void update_and_equals() {
        smartphoneDao.update(new Smartphone(1, "iphone", new BigDecimal(155)));
        assertEquals(smartphoneDao.getById(1).getModel(), "iphone");
        assertEquals(smartphoneDao.getById(1).getPrice(), new BigDecimal(155));
    }

    @Test
    void delete_smartphone_test() {
        assertNotNull(smartphoneDao.getById(2));
        smartphoneDao.delete(2);
        assertNull(smartphoneDao.getById(2));
    }
}

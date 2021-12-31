package com.product.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DBUtilGetConnectionTest {
    @Test
    void is_connection_presented() {
        assertNotNull(JdbcDataSource.getConnection());
    }
}

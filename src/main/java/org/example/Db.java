package org.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Db {
    static Logger logger = LogManager.getLogger(Db.class.getName());

    private final DataSource ds;

    public Db(DataSource ds) {
        this.ds = ds;
    }

    private static final String SQL_SELECT_STATE = "SELECT state FROM state WHERE userId = ? AND chatId = ?";
    public int getState(Integer userId, Long chatId) {
        int state = 0;
        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_STATE)) {

            preparedStatement.setInt(1, userId);
            preparedStatement.setLong(2, chatId);
            final ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                state = result.getInt("state");
            }

        } catch (SQLException e) {
            logger.error(e);
        }
        return state;
    }

}

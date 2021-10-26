package dev.elliotfrost.anarchybot.database;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseToDo {
    private final DataSource ds;

    DatabaseToDo(DataSource ds) {
        this.ds = ds;
    }

    public void getToDoList(String channelId) {
        try {
            Connection connection = this.ds.getConnection();
            PreparedStatement statement = connection.prepareStatement(String.format("SELECT * FROM ToDo WHERE channelId = %s", channelId), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = statement.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void postToDo(String channelId, String Task) {
        try {
            Connection connection = this.ds.getConnection();
            PreparedStatement statement = connection.prepareStatement(String.format("SELECT * FROM ToDo WHERE channelId = %s", channelId), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = statement.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

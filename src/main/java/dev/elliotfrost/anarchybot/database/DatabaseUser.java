package dev.elliotfrost.anarchybot.database;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseUser {
    private final DataSource ds;

    public DatabaseUser(DataSource ds) {
        this.ds = ds;
    }
    public void newUser(String userid) {
        try {
            Connection connection = this.ds.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO User (userid) VALUE ?");
            statement.setString(1,userid);
            statement.execute();

            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void linkJava(String userid, String username) {
        try {
            Connection connection = this.ds.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE User SET java = ? WHERE userid = ?");
            statement.setString(1,username);
            statement.setString(2,userid);
            statement.execute();

            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void linkBedrock(String userid, String username) {
        try {
            Connection connection = this.ds.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE User SET bedrock = ? WHERE userid = ?");
            statement.setString(1,username);
            statement.setString(2,userid);
            statement.execute();
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public String getAccounts(String userid) {
        String accounts = null;
        try {
            Connection connection = this.ds.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM User WHERE userid = ?");
            statement.setString(1,userid);
            statement.executeQuery().last();
            System.out.print(statement);
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return accounts;
    }
}

package dev.elliotfrost.anarchybot.database;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUser {
    private final DataSource ds;

    public DatabaseUser(DataSource ds) {
        this.ds = ds;
    }

    public void linkJava(String userid, String username) {
        if (!checkIfUserExists(userid)) { addUser(userid); }
        try {
            Connection connection = this.ds.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE User SET java = ? WHERE userid = ?");
            statement.setString(1,username);
            statement.setString(2,userid);
            statement.execute();

            connection.close();

            System.out.println("Tried to link Java");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void linkBedrock(String userid, String username) {
        if (!checkIfUserExists(userid)) { addUser(userid); }
        try {
            Connection connection = this.ds.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE User SET bedrock = ? WHERE userid = ?");
            statement.setString(1,username);
            statement.setString(2,userid);
            statement.execute();
            connection.close();
            System.out.println("Tried to link Bedrock");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public List<String> getAccounts(String userid) {
        List<String> accounts = new ArrayList<>();
        try {
            Connection connection = this.ds.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM User WHERE userid = ?");
            statement.setString(1,userid);
            ResultSet results = statement.executeQuery();
            if (results.next()) {
                accounts.add(results.getString(3));
                accounts.add(results.getString(4));
            } else {
                System.out.println("ERR: UserNotFound in DB");
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return accounts;
    }
    public boolean checkIfUserExists(String userid) {
        try {
            Connection connection = this.ds.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM User WHERE userid = ?");
            statement.setString(1,userid);
            ResultSet rest = statement.executeQuery();
            boolean exists = rest.next();
            System.out.println(exists + " (Checked if a user exists): ");
            connection.close();
            return exists;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public void addUser(String userid) {
        try {
            Connection connection = this.ds.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO User (id, userid, java, bedrock) VALUES (NULL, ? ,NULL, NULL)");
            statement.setString(1, userid);
            statement.execute();
            connection.close();
            System.out.println("Supposed to have Inserted a user");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

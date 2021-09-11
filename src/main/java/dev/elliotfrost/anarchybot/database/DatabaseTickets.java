package dev.elliotfrost.anarchybot.database;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseTickets {
    private final DataSource ds;

    DatabaseTickets(DataSource ds) {
        this.ds = ds;
    }

    public int getTicketNumber(String authorId) {
        try {
            Connection connnection = this.ds.getConnection();
            PreparedStatement statement = connnection.prepareStatement(String.format("SELECT * FROM Tickets WHERE AuthorId = %s", authorId), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = statement.executeQuery();

            System.out.println(result);

            result.last();
            int ticketNo = result.getRow();
            connnection.close();
            return ticketNo;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
 }

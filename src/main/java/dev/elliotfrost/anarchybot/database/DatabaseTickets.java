package dev.elliotfrost.anarchybot.database;

import dev.elliotfrost.anarchybot.Bot;

import javax.sql.DataSource;
import java.sql.*;

public class DatabaseTickets {
    private final DataSource ds;

    DatabaseTickets(DataSource ds) {
        this.ds = ds;
    }

    public int getTicketNumber(String authorId) {
        try {
            Connection connection = this.ds.getConnection();
            PreparedStatement statement = connection.prepareStatement(String.format("SELECT * FROM Tickets WHERE AuthorId = %s", authorId), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = statement.executeQuery();

            result.last();
            int ticketNo = result.getRow();
            connection.close();
            return ticketNo;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
    public boolean postNewTicket(String authorId, String ChannelID, String reason) {
        long unixTime = System.currentTimeMillis() / 1000L;
        try {
            Connection connection = this.ds.getConnection();
            Statement statement = connection.createStatement();
            boolean resultSet = statement.execute(String.format("INSERT INTO Tickets (authorId, channelId, reason, timeOpened) VALUES ('%s', '%s', '%s', '%o')", authorId, ChannelID, reason, unixTime));

            connection.close();
            return resultSet;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
 }

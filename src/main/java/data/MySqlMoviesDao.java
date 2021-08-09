package data;

import com.mysql.cj.jdbc.Driver;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlMoviesDao implements MoviesDao{

    private Connection connection = null;

    public MySqlMoviesDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());

            this.connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );

        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public List<Movie> all() throws SQLException {
        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery("SELECT * FROM movies");

        List<Movie> movies = new ArrayList<>();

        while (rs.next()) {
            movies.add(new Movie(
                    rs.getString("title"),
                    rs.getString("year"),
                    rs.getString("rating"),
                    rs.getString("plot"),
                    rs.getInt("id")
            ));
        }

        return movies;
    }

    @Override
    public Movie findOne(int id) {
        return null;
    }

    @Override
    public void insert(Movie movie) {

    }

    @Override
    public void insertAll(Movie[] movies) throws SQLException {

        // Build sql template
        StringBuilder sql = new StringBuilder("INSERT INTO movies (" +
                "title, year, rating, plot, id) " +
                "VALUES ");


        // Add a interpolation template for each element in movies list
        sql.append("(?, ?, ?, ?, ?), ".repeat(movies.length));

        // Create a new String and take off the last comma and whitespace
        sql = new StringBuilder(sql.substring(0, sql.length() - 2));

        // Use the sql string to create a prepared statement
        PreparedStatement statement = connection.prepareStatement(sql.toString());

        // Add each movie to the prepared statement using the index of each sql param: '?'
        // This is done by using a counter
        // You could use a for loop to do this as well and use its incrementor
        int counter = 0;
        for (Movie movie : movies) {
            statement.setString((counter * 5) + 1, movie.getTitle());
            statement.setString((counter * 5) + 2, movie.getYear());
            statement.setString((counter * 5) + 3, movie.getRating());
            statement.setString((counter * 5) + 4, movie.getPlot());
            statement.setInt((counter * 5) + 5, movie.getId());
            counter++;
        }
        statement.executeUpdate();
    }

    @Override
    public void update(Movie movie) throws SQLException {
        String sql = "UPDATE movies" +
                "SET title = ?" +
                "SET year = ?" +
                "SET rating = ?" +
                "SET plot = ?" +
                "WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql.toString());

        statement.setString(1, movie.getTitle());
        statement.setString(2, movie.getYear());
        statement.setString(3, movie.getRating());
        statement.setString(4, movie.getPlot());
        statement.setInt(5, movie.getId());

        statement.execute();

    }

    @Override
    public void destroy(int id) throws SQLException {
        String sql = "DELETE FROM movies " +
                "WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql.toString());

        statement.setInt(1, id);

        statement.execute();
    }
}

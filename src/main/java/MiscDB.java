import java.sql.*;
import java.util.Vector;

public class MiscDB {

    private static final String DB_CONNECTION_URL = "jdbc:sqlite:misc.db";


    private static final String TABLE_NAME = "misc";
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String MISC_ONE_COLUMN = "miscOne";
    private static final String MISC_TWO_COLUMN = "miscTwo";
    private static final String MISC_THREE_COLUMN = "miscThree";
    private static final String MISC_FOUR_COLUMN = "miscFour";
    private static final String MISC_FIVE_COLUMN = "miscFive";
    private static final String MISC_SIX_COLUMN = "miscSix";
    private static final String MISC_SEVEN_COLUMN = "miscSeven";
    private static final String MISC_EIGHT_COLUMN = "miscEight";
    private static final String MISC_NINE_COLUMN = "miscNine";
    private static final String MISC_TEN_COLUMN = "miscTen";

    private static final String LOAD_MISC = "SELECT * FROM misc WHERE id = ?";

    private static final String SAVE_MISC = "UPDATE misc SET miscOne = ?, miscTwo = ?, miscThree = ?, miscFour = ?, miscFive = ?, miscSix = ?, miscSeven = ?, miscEight = ?, miscNine = ?, miscTen = ? WHERE id = ?";

    private static final String ADD_MISC = "INSERT INTO misc (name, miscOne, miscTwo, miscThree, miscFour, miscFive, miscSix, miscSeven, miscEight, miscNine, miscTen) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    private static final String DELETE_MISC = "DELETE FROM misc WHERE ID = ?";


    void createMiscTable() {

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL);
             Statement statement = conn.createStatement()) {

            String createTableSQLTemplate = "CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)";
            String createTableSQL = String.format(createTableSQLTemplate, TABLE_NAME, ID_COLUMN, NAME_COLUMN, MISC_ONE_COLUMN, MISC_TWO_COLUMN, MISC_THREE_COLUMN, MISC_FOUR_COLUMN, MISC_FIVE_COLUMN, MISC_SIX_COLUMN, MISC_SEVEN_COLUMN, MISC_EIGHT_COLUMN, MISC_NINE_COLUMN, MISC_TEN_COLUMN);

            statement.executeUpdate(createTableSQL);

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }

    Vector loadMisc(String ID) {

        Vector misc = new Vector();

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(LOAD_MISC)) {

            preparedStatement.setString(1, ID);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String one = rs.getString("miscOne");
                misc.add(one);
                String two = rs.getString("miscTwo");
                misc.add(two);
                String three = rs.getString("miscThree");
                misc.add(three);
                String four = rs.getString("miscFour");
                misc.add(four);
                String five = rs.getString("miscFive");
                misc.add(five);
                String six = rs.getString("miscSix");
                misc.add(six);
                String seven = rs.getString("miscSeven");
                misc.add(seven);
                String eight = rs.getString("miscEight");
                misc.add(eight);
                String nine = rs.getString("miscNine");
                misc.add(nine);
                String ten = rs.getString("miscTen");
                misc.add(ten);
            }
            return misc;
        } catch (SQLException sqle) {
            throw  new RuntimeException(sqle);
        }
    }

    void saveMisc(Vector miscList) {

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_MISC)) {

            String one = miscList.get(0).toString();
            preparedStatement.setString(1, one);
            String two = miscList.get(1).toString();
            preparedStatement.setString(2, two);
            String three = miscList.get(2).toString();
            preparedStatement.setString(3, three);
            String four = miscList.get(3).toString();
            preparedStatement.setString(4, four);
            String five = miscList.get(4).toString();
            preparedStatement.setString(5, five);
            String six = miscList.get(5).toString();
            preparedStatement.setString(6, six);
            String seven = miscList.get(6).toString();
            preparedStatement.setString(7, seven);
            String eight = miscList.get(7).toString();
            preparedStatement.setString(8, eight);
            String nine = miscList.get(8).toString();
            preparedStatement.setString(9, nine);
            String ten = miscList.get(9).toString();
            preparedStatement.setString(10, ten);
            String ID = miscList.get(10).toString();
            preparedStatement.setString(11, ID);

            preparedStatement.executeUpdate();

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }

    }


    void addMisc(Vector miscList) {

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_MISC)) {


            String name = miscList.get(10).toString();
            preparedStatement.setString(1, name);
            String one = miscList.get(0).toString();
            preparedStatement.setString(2, one);
            String two = miscList.get(1).toString();
            preparedStatement.setString(3, two);
            String three = miscList.get(2).toString();
            preparedStatement.setString(4, three);
            String four = miscList.get(3).toString();
            preparedStatement.setString(5, four);
            String five = miscList.get(4).toString();
            preparedStatement.setString(6, five);
            String six = miscList.get(5).toString();
            preparedStatement.setString(7, six);
            String seven = miscList.get(6).toString();
            preparedStatement.setString(8, seven);
            String eight = miscList.get(7).toString();
            preparedStatement.setString(9, eight);
            String nine = miscList.get(8).toString();
            preparedStatement.setString(10, nine);
            String ten = miscList.get(9).toString();
            preparedStatement.setString(11, ten);


            preparedStatement.executeUpdate();

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }

    }

    void deleteMisc(String ID) {

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MISC)) {

            preparedStatement.setString(1, ID);

            preparedStatement.executeUpdate();

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }
}




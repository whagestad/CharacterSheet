import java.sql.*;
import java.util.Vector;

public class ItemDB {

    private static final String DB_CONNECTION_URL = "jdbc:sqlite:items.db";


    private static final String TABLE_NAME = "items";
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String ITEM_ONE_COLUMN = "itemOne";
    private static final String ITEM_TWO_COLUMN = "itemTwo";
    private static final String ITEM_THREE_COLUMN = "itemThree";
    private static final String ITEM_FOUR_COLUMN = "itemFour";
    private static final String ITEM_FIVE_COLUMN = "itemFive";
    private static final String ITEM_SIX_COLUMN = "itemSix";
    private static final String ITEM_SEVEN_COLUMN = "itemSeven";
    private static final String ITEM_EIGHT_COLUMN = "itemEight";
    private static final String ITEM_NINE_COLUMN = "itemNine";
    private static final String ITEM_TEN_COLUMN = "itemTen";

    private static final String LOAD_ITEMS = "SELECT * FROM items WHERE id = ?";

    private static final String SAVE_ITEMS = "UPDATE items SET itemOne = ?, itemTwo = ?, itemThree = ?, itemFour = ?, itemFive = ?, itemSix = ?, itemSeven = ?, itemEight = ?, itemNine = ?, itemTen = ? WHERE id = ?";

    private static final String ADD_ITEMS = "INSERT INTO items (name, itemOne, itemTwo, itemThree, itemFour, itemFive, itemSix, itemSeven, itemEight, itemNine, itemTen) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String DELETE_ITEMS = "DELETE FROM items WHERE ID = ?";


    void createItemTable() {

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL);
             Statement statement = conn.createStatement()) {

            String createTableSQLTemplate = "CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)";
            String createTableSQL = String.format(createTableSQLTemplate, TABLE_NAME, ID_COLUMN, NAME_COLUMN, ITEM_ONE_COLUMN, ITEM_TWO_COLUMN, ITEM_THREE_COLUMN, ITEM_FOUR_COLUMN, ITEM_FIVE_COLUMN, ITEM_SIX_COLUMN, ITEM_SEVEN_COLUMN, ITEM_EIGHT_COLUMN, ITEM_NINE_COLUMN, ITEM_TEN_COLUMN);

            statement.executeUpdate(createTableSQL);

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }

    Vector loadItems(String ID) {

        Vector items = new Vector();

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(LOAD_ITEMS)) {

            preparedStatement.setString(1, ID);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String one = rs.getString("itemOne");
                items.add(one);
                String two = rs.getString("itemTwo");
                items.add(two);
                String three = rs.getString("itemThree");
                items.add(three);
                String four = rs.getString("itemFour");
                items.add(four);
                String five = rs.getString("itemFive");
                items.add(five);
                String six = rs.getString("itemSix");
                items.add(six);
                String seven = rs.getString("itemSeven");
                items.add(seven);
                String eight = rs.getString("itemEight");
                items.add(eight);
                String nine = rs.getString("itemNine");
                items.add(nine);
                String ten = rs.getString("itemTen");
                items.add(ten);
            }
            return items;
        } catch (SQLException sqle) {
            throw  new RuntimeException(sqle);
        }
    }

    void saveItems(Vector itemList) {

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_ITEMS)) {

            String one = itemList.get(0).toString();
            preparedStatement.setString(1, one);
            String two = itemList.get(1).toString();
            preparedStatement.setString(2, two);
            String three = itemList.get(2).toString();
            preparedStatement.setString(3, three);
            String four = itemList.get(3).toString();
            preparedStatement.setString(4, four);
            String five = itemList.get(4).toString();
            preparedStatement.setString(5, five);
            String six = itemList.get(5).toString();
            preparedStatement.setString(6, six);
            String seven = itemList.get(6).toString();
            preparedStatement.setString(7, seven);
            String eight = itemList.get(7).toString();
            preparedStatement.setString(8, eight);
            String nine = itemList.get(8).toString();
            preparedStatement.setString(9, nine);
            String ten = itemList.get(9).toString();
            preparedStatement.setString(10, ten);
            String ID = itemList.get(10).toString();
            preparedStatement.setString(11, ID);

            preparedStatement.executeUpdate();

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }

    }


    void addItems(Vector itemList) {

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_ITEMS)) {


            String name = itemList.get(10).toString();
            preparedStatement.setString(1, name);
            String one = itemList.get(0).toString();
            preparedStatement.setString(2, one);
            String two = itemList.get(1).toString();
            preparedStatement.setString(3, two);
            String three = itemList.get(2).toString();
            preparedStatement.setString(4, three);
            String four = itemList.get(3).toString();
            preparedStatement.setString(5, four);
            String five = itemList.get(4).toString();
            preparedStatement.setString(6, five);
            String six = itemList.get(5).toString();
            preparedStatement.setString(7, six);
            String seven = itemList.get(6).toString();
            preparedStatement.setString(8, seven);
            String eight = itemList.get(7).toString();
            preparedStatement.setString(9, eight);
            String nine = itemList.get(8).toString();
            preparedStatement.setString(10, nine);
            String ten = itemList.get(9).toString();
            preparedStatement.setString(11, ten);


            preparedStatement.executeUpdate();

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }

    }

    void deleteItems(String ID) {

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ITEMS)) {

            preparedStatement.setString(1, ID);

            preparedStatement.executeUpdate();

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }

}

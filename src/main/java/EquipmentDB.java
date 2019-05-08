import java.sql.*;
import java.util.Vector;

public class EquipmentDB {

    private static final String DB_CONNECTION_URL = "jdbc:sqlite:equipment.db";

    private static final String TABLE_NAME = "equipment";
    private static final String NAME_COLUMN = "name";
    private static final String ID_COLUMN = "id";
    private static final String EQUIP_ONE_COLUMN = "equipmentOne";
    private static final String EQUIP_TWO_COLUMN = "equipmentTwo";
    private static final String EQUIP_THREE_COLUMN = "equipmentThree";
    private static final String EQUIP_FOUR_COLUMN = "equipmentFour";
    private static final String EQUIP_FIVE_COLUMN = "equipmentFive";
    private static final String EQUIP_SIX_COLUMN = "equipmentSix";
    private static final String EQUIP_SEVEN_COLUMN = "equipmentSeven";
    private static final String EQUIP_EIGHT_COLUMN = "equipmentEight";
    private static final String EQUIP_NINE_COLUMN = "equipmentNine";
    private static final String EQUIP_TEN_COLUMN = "equipmentTen";

    private static final String LOAD_EQUIPMENT = "SELECT * FROM equipment WHERE id = ?";

    private static final String SAVE_EQUIPMENT = "UPDATE equipment SET equipmentOne = ?, equipmentTwo = ?, equipmentThree = ?, equipmentFour = ?, equipmentFive = ?, equipmentSix = ?, equipmentSeven = ?, equipmentEight = ?, equipmentNine = ?, equipmentTen = ? WHERE id = ?";

    private static final String ADD_EQUIPMENT = "INSERT INTO equipment (name, equipmentOne, equipmentTwo, equipmentThree, equipmentFour, equipmentFive, equipmentSix, equipmentSeven, equipmentEight, equipmentNine, equipmentTen) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String DELETE_EQUIPMENT = "DELETE FROM equipment WHERE ID = ?";


    void createEquipmentTable() {

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL);
             Statement statement = conn.createStatement()) {

            String createTableSQLTemplate = "CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)";
            String createTableSQL = String.format(createTableSQLTemplate, TABLE_NAME, ID_COLUMN, NAME_COLUMN, EQUIP_ONE_COLUMN, EQUIP_TWO_COLUMN, EQUIP_THREE_COLUMN, EQUIP_FOUR_COLUMN, EQUIP_FIVE_COLUMN, EQUIP_SIX_COLUMN, EQUIP_SEVEN_COLUMN, EQUIP_EIGHT_COLUMN, EQUIP_NINE_COLUMN, EQUIP_TEN_COLUMN);

            statement.executeUpdate(createTableSQL);
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }

    Vector loadEquipment(String ID) {

        Vector equipment = new Vector();

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(LOAD_EQUIPMENT)) {

            preparedStatement.setString(1, ID);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String one = rs.getString("equipmentOne");
                equipment.add(one);
                String two = rs.getString("equipmentTwo");
                equipment.add(two);
                String three = rs.getString("equipmentThree");
                equipment.add(three);
                String four = rs.getString("equipmentFour");
                equipment.add(four);
                String five = rs.getString("equipmentFive");
                equipment.add(five);
                String six = rs.getString("equipmentSix");
                equipment.add(six);
                String seven = rs.getString("equipmentSeven");
                equipment.add(seven);
                String eight = rs.getString("equipmentEight");
                equipment.add(eight);
                String nine = rs.getString("equipmentNine");
                equipment.add(nine);
                String ten = rs.getString("equipmentTen");
                equipment.add(ten);
            }
            return equipment;
        } catch (SQLException sqle) {
            throw  new RuntimeException(sqle);
        }
    }

    void saveEquipment(Vector equipmentList) {

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_EQUIPMENT)) {

            String one = equipmentList.get(0).toString();
            preparedStatement.setString(1, one);
            String two = equipmentList.get(1).toString();
            preparedStatement.setString(2, two);
            String three = equipmentList.get(2).toString();
            preparedStatement.setString(3, three);
            String four = equipmentList.get(3).toString();
            preparedStatement.setString(4, four);
            String five = equipmentList.get(4).toString();
            preparedStatement.setString(5, five);
            String six = equipmentList.get(5).toString();
            preparedStatement.setString(6, six);
            String seven = equipmentList.get(6).toString();
            preparedStatement.setString(7, seven);
            String eight = equipmentList.get(7).toString();
            preparedStatement.setString(8, eight);
            String nine = equipmentList.get(8).toString();
            preparedStatement.setString(9, nine);
            String ten = equipmentList.get(9).toString();
            preparedStatement.setString(10, ten);
            String ID = equipmentList.get(10).toString();
            preparedStatement.setString(11, ID);

            preparedStatement.executeUpdate();

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }

    }


    void addEquipment(Vector equipmentList) {

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_EQUIPMENT)) {


            String name = equipmentList.get(10).toString();
            preparedStatement.setString(1, name);
            String one = equipmentList.get(0).toString();
            preparedStatement.setString(2, one);
            String two = equipmentList.get(1).toString();
            preparedStatement.setString(3, two);
            String three = equipmentList.get(2).toString();
            preparedStatement.setString(4, three);
            String four = equipmentList.get(3).toString();
            preparedStatement.setString(5, four);
            String five = equipmentList.get(4).toString();
            preparedStatement.setString(6, five);
            String six = equipmentList.get(5).toString();
            preparedStatement.setString(7, six);
            String seven = equipmentList.get(6).toString();
            preparedStatement.setString(8, seven);
            String eight = equipmentList.get(7).toString();
            preparedStatement.setString(9, eight);
            String nine = equipmentList.get(8).toString();
            preparedStatement.setString(10, nine);
            String ten = equipmentList.get(9).toString();
            preparedStatement.setString(11, ten);


            preparedStatement.executeUpdate();

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }

    }

    void deleteEquipment(String ID) {

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EQUIPMENT)) {

            preparedStatement.setString(1, ID);

            preparedStatement.executeUpdate();

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }

}


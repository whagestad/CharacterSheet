import java.sql.*;
import java.util.Vector;

public class SkillDB {

    private static final String DB_CONNECTION_URL = "jdbc:sqlite:skills.db";

    private static final String TABLE_NAME = "skills";
    private static final String NAME_COLUMN = "name";
    private static final String ID_COLUMN = "id";
    private static final String SKILL_ONE_COLUMN = "skillOne";
    private static final String SKILL_TWO_COLUMN = "skillTwo";
    private static final String SKILL_THREE_COLUMN = "skillThree";
    private static final String SKILL_FOUR_COLUMN = "skillFour";
    private static final String SKILL_FIVE_COLUMN = "skillFive";
    private static final String SKILL_SIX_COLUMN = "skillSix";
    private static final String SKILL_SEVEN_COLUMN = "skillSeven";
    private static final String SKILL_EIGHT_COLUMN = "skillEight";
    private static final String SKILL_NINE_COLUMN = "skillNine";
    private static final String SKILL_TEN_COLUMN = "skillTen";

    private static final String LOAD_SKILLS = "SELECT * FROM skills WHERE id = ?";

    private static final String SAVE_SKILLS = "UPDATE skills SET skillOne = ?, skillTwo = ?, skillThree = ?, skillFour = ?, skillFive = ?, skillSix = ?, skillSeven = ?, skillEight = ?, skillNine = ?, skillTen = ? WHERE id = ?";

    private static final String ADD_SKILLS = "INSERT INTO skills (name, skillOne, skillTwo, skillThree, skillFour, skillFive, skillSix, skillSeven, skillEight, skillNine, skillTen) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String DELETE_SKILLS = "DELETE FROM skills WHERE ID = ?";

    void createSkillTable() {

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL);
             Statement statement = conn.createStatement()) {

            String createTableSQLTemplate = "CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)";
            String createTableSQL = String.format(createTableSQLTemplate, TABLE_NAME, ID_COLUMN, NAME_COLUMN, SKILL_ONE_COLUMN, SKILL_TWO_COLUMN, SKILL_THREE_COLUMN, SKILL_FOUR_COLUMN, SKILL_FIVE_COLUMN, SKILL_SIX_COLUMN, SKILL_SEVEN_COLUMN, SKILL_EIGHT_COLUMN, SKILL_NINE_COLUMN, SKILL_TEN_COLUMN);

            statement.executeUpdate(createTableSQL);
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }

    Vector loadSkills(Integer ID) {

        Vector skills = new Vector();

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(LOAD_SKILLS)) {

            preparedStatement.setInt(1, ID);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String one = rs.getString("skillOne");
                skills.add(one);
                String two = rs.getString("skillTwo");
                skills.add(two);
                String three = rs.getString("skillThree");
                skills.add(three);
                String four = rs.getString("skillFour");
                skills.add(four);
                String five = rs.getString("skillFive");
                skills.add(five);
                String six = rs.getString("skillSix");
                skills.add(six);
                String seven = rs.getString("skillSeven");
                skills.add(seven);
                String eight = rs.getString("skillEight");
                skills.add(eight);
                String nine = rs.getString("skillNine");
                skills.add(nine);
                String ten = rs.getString("skillTen");
                skills.add(ten);
            }
            return skills;
        } catch (SQLException sqle) {
            throw  new RuntimeException(sqle);
        }
    }

    void saveSkills(Vector skillList) {

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SKILLS)) {

            String one = skillList.get(0).toString();
            preparedStatement.setString(1, one);
            String two = skillList.get(1).toString();
            preparedStatement.setString(2, two);
            String three = skillList.get(2).toString();
            preparedStatement.setString(3, three);
            String four = skillList.get(3).toString();
            preparedStatement.setString(4, four);
            String five = skillList.get(4).toString();
            preparedStatement.setString(5, five);
            String six = skillList.get(5).toString();
            preparedStatement.setString(6, six);
            String seven = skillList.get(6).toString();
            preparedStatement.setString(7, seven);
            String eight = skillList.get(7).toString();
            preparedStatement.setString(8, eight);
            String nine = skillList.get(8).toString();
            preparedStatement.setString(9, nine);
            String ten = skillList.get(9).toString();
            preparedStatement.setString(10, ten);
            String ID = skillList.get(10).toString();
            preparedStatement.setString(11, ID);

            preparedStatement.executeUpdate();

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }

    }


    void addSkills(Vector skillList) {

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_SKILLS)) {


            String name = skillList.get(10).toString();
            preparedStatement.setString(1, name);
            String one = skillList.get(0).toString();
            preparedStatement.setString(2, one);
            String two = skillList.get(1).toString();
            preparedStatement.setString(3, two);
            String three = skillList.get(2).toString();
            preparedStatement.setString(4, three);
            String four = skillList.get(3).toString();
            preparedStatement.setString(5, four);
            String five = skillList.get(4).toString();
            preparedStatement.setString(6, five);
            String six = skillList.get(5).toString();
            preparedStatement.setString(7, six);
            String seven = skillList.get(6).toString();
            preparedStatement.setString(8, seven);
            String eight = skillList.get(7).toString();
            preparedStatement.setString(9, eight);
            String nine = skillList.get(8).toString();
            preparedStatement.setString(10, nine);
            String ten = skillList.get(9).toString();
            preparedStatement.setString(11, ten);


            preparedStatement.executeUpdate();

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }

    }

    void deleteSkills(String ID) {

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SKILLS)) {

            preparedStatement.setString(1, ID);

            preparedStatement.executeUpdate();

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }


}


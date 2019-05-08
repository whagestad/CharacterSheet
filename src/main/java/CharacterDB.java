import java.sql.*;
import java.util.HashMap;
import java.util.Vector;

public class CharacterDB {

    private static final String DB_CONNECTION_URL = "jdbc:sqlite:characters.db";

    private static final String TABLE_NAME = "characters";
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String CLASS_COLUMN = "class";
    private static final String RACE_COLUMN = "race";
    private static final String ALIGN_COLUMN = "alignment";
    private static final String LEVEL_COLUMN = "level";
    private static final String EXP_COLUMN = "experience";
    private static final String NEXT_EXP_COLUMN = "expNextLevel";
    private static final String TOTAL_HP_COLUMN = "totalHP";
    private static final String CURRENT_HP_COLUMN = "currentHP";
    private static final String STRENGTH_COLUMN = "strength";
    private static final String DEXTERITY_COLUMN = "dexterity";
    private static final String CONSTITUTION_COLUMN = "constitution";
    private static final String INTELLIGENCE_COLUMN = "intelligence";
    private static final String WISDOM_COLUMN = "wisdom";
    private static final String CHARISMA_COLUMN = "charisma";
    private static final String ARMOR_COLUMN = "armorClass";
    private static final String SPEED_COLUMN = "speed";
    private static final String STATUS_ONE_COLUMN = "conditionOne";
    private static final String STATUS_TWO_COLUMN = "conditionTwo";
    private static final String STATUS_THREE_COLUMN = "conditionThree";

    private static final String FETCH_ALL_CHARACTERS = "SELECT * FROM characters ORDER BY id ASC";

    private static final String LOAD_CHARACTER = "SELECT * FROM characters WHERE id = ?";

    private static final String ADD_CHARACTER = "INSERT INTO characters (name, class, race, alignment, level, experience, expNextLevel, totalHP, currentHP, strength, dexterity, constitution, intelligence, wisdom, charisma, armorClass, speed, conditionOne, conditionTwo, conditionThree) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SAVE_CHARACTER = "UPDATE characters SET name = ?, class = ?, race = ?, alignment = ?, level = ?, experience = ?, expNextLevel = ?, totalHP = ?, currentHP = ?, strength = ?, dexterity = ?, constitution = ?, intelligence = ?, wisdom = ?, charisma = ?, armorClass = ?, speed = ?, conditionOne = ?, conditionTwo = ?, conditionThree = ? WHERE id = ?";

    private static final String DELETE_CHARACTER = "DELETE FROM characters WHERE id = ?";



    void createCharacterTable() {

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL);
        Statement statement = conn.createStatement()) {

            String createTableSQLTemplate = "CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s TEXT, %s INTEGER, %s TEXT, %s TEXT, %s TEXT)";
            String createTableSQL = String.format(createTableSQLTemplate, TABLE_NAME, ID_COLUMN, NAME_COLUMN, CLASS_COLUMN, RACE_COLUMN, ALIGN_COLUMN, LEVEL_COLUMN, EXP_COLUMN, NEXT_EXP_COLUMN, TOTAL_HP_COLUMN, CURRENT_HP_COLUMN, STRENGTH_COLUMN, DEXTERITY_COLUMN, CONSTITUTION_COLUMN, INTELLIGENCE_COLUMN, WISDOM_COLUMN, CHARISMA_COLUMN, ARMOR_COLUMN, SPEED_COLUMN, STATUS_ONE_COLUMN, STATUS_TWO_COLUMN, STATUS_THREE_COLUMN);

            statement.executeUpdate(createTableSQL);

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }

    HashMap<String, String> fetchCharacterList() {

        HashMap<String, String> characters = new HashMap<>();

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
             Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery(FETCH_ALL_CHARACTERS);

            String id;
            int idInt;
            String name;

            while (rs.next()) {

                idInt = rs.getInt("id");
                id = String.valueOf(idInt);
                name = rs.getString("name");

                characters.put(id, name);

            }
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
        return characters;
    }

    Vector<String> loadCharacter(Integer ID) {

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(LOAD_CHARACTER)) {

            preparedStatement.setInt(1, ID);

            ResultSet rs = preparedStatement.executeQuery();

            Vector<String> characterInfo = new Vector<>();

            while (rs.next()) {

                String name = rs.getString("name");
                characterInfo.add(name);
                String classU = rs.getString("class");
                characterInfo.add(classU);
                String race = rs.getString("race");
                characterInfo.add(race);
                String alignment = rs.getString("alignment");
                characterInfo.add(alignment);
                String level = String.valueOf(rs.getInt("level"));
                characterInfo.add(level);
                String experience = String.valueOf(rs.getInt("experience"));
                characterInfo.add(experience);
                String expNextLevel = String.valueOf(rs.getInt("expNextLevel"));
                characterInfo.add(expNextLevel);
                String totalHP = String.valueOf(rs.getInt("totalHP"));
                characterInfo.add(totalHP);
                String currentHP = String.valueOf(rs.getInt("currentHP"));
                characterInfo.add(currentHP);
                String strength = String.valueOf(rs.getInt("strength"));
                characterInfo.add(strength);
                String dexterity = String.valueOf(rs.getInt("dexterity"));
                characterInfo.add(dexterity);
                String constitution = String.valueOf(rs.getInt("constitution"));
                characterInfo.add(constitution);
                String intelligence = String.valueOf(rs.getInt("intelligence"));
                characterInfo.add(intelligence);
                String wisdom = String.valueOf(rs.getInt("wisdom"));
                characterInfo.add(wisdom);
                String charisma = String.valueOf(rs.getInt("charisma"));
                characterInfo.add(charisma);
                String armorClass = rs.getString("armorClass");
                characterInfo.add(armorClass);
                String speed = String.valueOf(rs.getInt("speed"));
                characterInfo.add(speed);
                String conditionOne = rs.getString("conditionOne");
                characterInfo.add(conditionOne);
                String conditionTwo = rs.getString("conditionTwo");
                characterInfo.add(conditionTwo);
                String conditionThree = rs.getString("conditionThree");
                characterInfo.add(conditionThree);
                String idLabel = String.valueOf(rs.getInt("id"));
                characterInfo.add(idLabel);


            }
            //rs.close();
            //preparedStatement.close();
            //connection.close();

            return characterInfo;

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }

    void addCharacter(Vector<String> characterInfo) {

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_CHARACTER)) {

            String name = characterInfo.get(0);
            preparedStatement.setString(1, name);
            String classU = characterInfo.get(1);
            preparedStatement.setString(2, classU);
            String race = characterInfo.get(2);
            preparedStatement.setString(3, race);
            String alignment = characterInfo.get(3);
            preparedStatement.setString(4, alignment);
            String level = characterInfo.get(4);
            preparedStatement.setString(5, level);
            String experience = characterInfo.get(5);
            preparedStatement.setString(6, experience);
            String expNextLevel = characterInfo.get(6);
            preparedStatement.setString(7, expNextLevel);
            String totalHP = characterInfo.get(7);
            preparedStatement.setString(8, totalHP);
            String currentHP = characterInfo.get(8);
            preparedStatement.setString(9, currentHP);
            String strength = characterInfo.get(9);
            preparedStatement.setString(10, strength);
            String dexterity = characterInfo.get(10);
            preparedStatement.setString(11, dexterity);
            String constitution = characterInfo.get(11);
            preparedStatement.setString(12, constitution);
            String intelligence = characterInfo.get(12);
            preparedStatement.setString(13, intelligence);
            String wisdom = characterInfo.get(13);
            preparedStatement.setString(14, wisdom);
            String charisma = characterInfo.get(14);
            preparedStatement.setString(15, charisma);
            String armorClass = characterInfo.get(15);
            preparedStatement.setString(16, armorClass);
            String speed = characterInfo.get(16);
            preparedStatement.setString(17, speed);
            String conditionOne = characterInfo.get(17);
            preparedStatement.setString(18, conditionOne);
            String conditionTwo = characterInfo.get(18);
            preparedStatement.setString(19, conditionTwo);
            String conditionThree = characterInfo.get(19);
            preparedStatement.setString(20, conditionThree);

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }

    void saveCharacter(Vector<String> characterInfo) {

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_CHARACTER)) {

            String name = characterInfo.get(0);
            preparedStatement.setString(1, name);
            String classU =characterInfo.get(1);
            preparedStatement.setString(2, classU);
            String race = characterInfo.get(2);
            preparedStatement.setString(3, race);
            String alignment = characterInfo.get(3);
            preparedStatement.setString(4, alignment);
            Integer level = Integer.parseInt(characterInfo.get(4));
            preparedStatement.setInt(5, level);
            Integer experience = Integer.parseInt(characterInfo.get(5));
            preparedStatement.setInt(6, experience);
            Integer expNextLevel = Integer.parseInt(characterInfo.get(6));
            preparedStatement.setInt(7, expNextLevel);
            Integer totalHP = Integer.parseInt(characterInfo.get(7));
            preparedStatement.setInt(8, totalHP);
            Integer currentHP = Integer.parseInt(characterInfo.get(8));
            preparedStatement.setInt(9, currentHP);
            Integer strength = Integer.parseInt(characterInfo.get(9));
            preparedStatement.setInt(10, strength);
            Integer dexterity = Integer.parseInt(characterInfo.get(10));
            preparedStatement.setInt(11, dexterity);
            Integer constitution = Integer.parseInt(characterInfo.get(11));
            preparedStatement.setInt(12, constitution);
            Integer intelligence = Integer.parseInt(characterInfo.get(12));
            preparedStatement.setInt(13, intelligence);
            Integer wisdom = Integer.parseInt(characterInfo.get(13));
            preparedStatement.setInt(14, wisdom);
            Integer charisma = Integer.parseInt(characterInfo.get(14));
            preparedStatement.setInt(15, charisma);
            String armorClass = characterInfo.get(15);
            preparedStatement.setString(16, armorClass);
            Integer speed = Integer.parseInt(characterInfo.get(16));
            preparedStatement.setInt(17, speed);
            String conditionOne = characterInfo.get(17);
            preparedStatement.setString(18, conditionOne);
            String conditionTwo = characterInfo.get(18);
            preparedStatement.setString(19, conditionTwo);
            String conditionThree = characterInfo.get(19);
            preparedStatement.setString(20, conditionThree);
            Integer ID = Integer.parseInt(characterInfo.get(20));
            preparedStatement.setInt(21, ID);

            preparedStatement.executeUpdate();

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }
    }

    void deleteCharacter(Integer ID) {

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CHARACTER)) {

            preparedStatement.setInt(1, ID);

            preparedStatement.executeUpdate();

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        }

    }

}

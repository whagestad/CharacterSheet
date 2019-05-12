import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class ProgramGUI extends JFrame{

    private JTextField nameTextField;
    private JComboBox classComboBox;
    private JComboBox raceComboBox;
    private JComboBox alignmentComboBox;
    private JLabel levelLabel;
    private JSpinner experienceSpinner;
    private JButton levelUpButton;
    private JSpinner strengthSpinner;
    private JSpinner dexteritySpinner;
    private JSpinner constitutionSpinner;
    private JSpinner intelligenceSpinner;
    private JSpinner wisdomSpinner;
    private JSpinner charismaSpinner;
    private JSpinner totalHPspinner;
    private JSpinner currentHPspinner;
    private JComboBox armorClassCombo;
    private JSpinner speedSpinner;
    private JComboBox conditionOneCombo;
    private JComboBox conditionTwoCombo;
    private JComboBox conditionThreeCombo;
    private JComboBox dieTypeCombo;
    private JComboBox diceNumberCombo;
    private JTextArea diceTextArea;
    private JButton diceRollButton;
    private JButton saveButton;
    private JComboBox characterSelectCombo;
    private JButton loadButton;
    private JPanel characterPanel;
    private JButton deleteButton;
    private JLabel characterID;
    private JLabel expNextLevelLabel;
    private JButton clearDiceTextArea;
    private JTextArea notesTextArea;
    private JButton saveTextButton;

    private CharacterDB characterDB;
    private TextWriter textWriter;


    public ProgramGUI(CharacterSheet program) {

        setContentPane(characterPanel);
        setTitle("D&D Character Sheet Application");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        characterDB = new CharacterDB();
        textWriter = new TextWriter();

        frontLoader();
        pack();
        setVisible(true);

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Ensures that the character load function only occurs when an actual character is selected
                if (characterSelectCombo.getSelectedItem().toString().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please select a character to load");
                } else if (characterSelectCombo.getSelectedItem().toString().equals("No Characters Found")) {
                    JOptionPane.showMessageDialog(null, "No Characters Found. Please create and save a new character");
                } else if (characterSelectCombo.getSelectedItem().toString().equals("New Character")) {
                   int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to create a new character? This will action will clear all fields. Any unsaved information will be lost", "Warning", JOptionPane.YES_NO_OPTION);
                        if (response == JOptionPane.YES_OPTION) {
                            resetAllFields();
                            nameTextField.setEditable(true);
                            classComboBox.setEnabled(true);
                            raceComboBox.setEnabled(true);
                            alignmentComboBox.setEnabled(true);
                        }
                } else {
                    String selectedChar = characterSelectCombo.getSelectedItem().toString();
                    // Regex to get character ID from the combo box selection
                    Integer ID = Integer.parseInt(selectedChar.replaceAll("\\D", ""));
                    Vector<String> charInfo = characterDB.loadCharacter(ID);

                    nameTextField.setText(charInfo.get(0));
                    // Editing of the character name, class, race, and alignment is locked after
                    // loading the character for the first time
                    nameTextField.setEditable(false);
                    classComboBox.setSelectedItem(charInfo.get(1));
                    classComboBox.setEnabled(false);
                    raceComboBox.setSelectedItem(charInfo.get(2));
                    raceComboBox.setEnabled(false);
                    alignmentComboBox.setSelectedItem(charInfo.get(3));
                    alignmentComboBox.setEnabled(false);
                    levelLabel.setText(charInfo.get(4));
                    experienceSpinner.setValue((Integer.parseInt(charInfo.get(5))));
                    expNextLevelLabel.setText(charInfo.get(6));
                    totalHPspinner.setValue(Integer.parseInt(charInfo.get(7)));
                    currentHPspinner.setValue(Integer.parseInt(charInfo.get(8)));
                    strengthSpinner.setValue(Integer.parseInt(charInfo.get(9)));
                    dexteritySpinner.setValue(Integer.parseInt(charInfo.get(10)));
                    constitutionSpinner.setValue(Integer.parseInt(charInfo.get(11)));
                    intelligenceSpinner.setValue(Integer.parseInt(charInfo.get(12)));
                    wisdomSpinner.setValue(Integer.parseInt(charInfo.get(13)));
                    charismaSpinner.setValue(Integer.parseInt(charInfo.get(14)));
                    armorClassCombo.setSelectedItem(charInfo.get(15));
                    speedSpinner.setValue(Integer.parseInt(charInfo.get(16)));
                    conditionOneCombo.setSelectedItem(charInfo.get(17));
                    conditionTwoCombo.setSelectedItem(charInfo.get(18));
                    conditionThreeCombo.setSelectedItem(charInfo.get(19));
                    characterID.setText(charInfo.get(20));
                    notesTextArea.setText("");
                    textWriter.readText(notesTextArea, characterID.getText());
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // if and ID has not been assigned to the character, it has not been added to the database
                // first part of loop gathers entered information and adds it to the database, assigns ID
                if (characterID.getText().equals("")) {

                    Vector<String> charInfo = new Vector<>();

                    String name = nameTextField.getText();
                    charInfo.add(name);
                    String classU = classComboBox.getSelectedItem().toString();
                    charInfo.add(classU);
                    String race = raceComboBox.getSelectedItem().toString();
                    charInfo.add(race);
                    String alignment = alignmentComboBox.getSelectedItem().toString();
                    charInfo.add(alignment);
                    String level = levelLabel.getText();
                    charInfo.add(level);
                    String experience = experienceSpinner.getValue().toString();
                    charInfo.add(experience);
                    String expNextLevel = String.valueOf(expNextLevelLabel.getText());
                    charInfo.add(expNextLevel);
                    String totalHP = String.valueOf(totalHPspinner.getValue());
                    charInfo.add(totalHP);
                    String currentHP = String.valueOf(currentHPspinner.getValue());
                    charInfo.add(currentHP);
                    String strength = String.valueOf(strengthSpinner.getValue());
                    charInfo.add(strength);
                    String dexterity = String.valueOf(dexteritySpinner.getValue());
                    charInfo.add(dexterity);
                    String constitution = String.valueOf(constitutionSpinner.getValue());
                    charInfo.add(constitution);
                    String intelligence = String.valueOf(intelligenceSpinner.getValue());
                    charInfo.add(intelligence);
                    String wisdom = String.valueOf(wisdomSpinner.getValue());
                    charInfo.add(wisdom);
                    String charisma = String.valueOf(charismaSpinner.getValue());
                    charInfo.add(charisma);
                    String armorClass = armorClassCombo.getSelectedItem().toString();
                    charInfo.add(armorClass);
                    String speed = String.valueOf(speedSpinner.getValue());
                    charInfo.add(speed);
                    String conditionOne = conditionOneCombo.getSelectedItem().toString();
                    charInfo.add(conditionOne);
                    String conditionTwo = conditionTwoCombo.getSelectedItem().toString();
                    charInfo.add(conditionTwo);
                    String conditionThree = conditionThreeCombo.getSelectedItem().toString();
                    charInfo.add(conditionThree);
                    characterDB.addCharacter(charInfo);

                    String id = characterDB.fetchNewID();
                    characterID.setText(id);
                    textWriter.createFile(id);
                    textWriter.writeText(notesTextArea, id);

                    JOptionPane.showMessageDialog(characterPanel, "Character Saved");

                    loadCharCombo();

                // if the character has an ID, then the program updates the
                // existing information in the database using that ID
                } else {

                    Vector<String> charInfo = new Vector<>();

                    String name = nameTextField.getText();
                    charInfo.add(name);
                    String classU = classComboBox.getSelectedItem().toString();
                    charInfo.add(classU);
                    String race = raceComboBox.getSelectedItem().toString();
                    charInfo.add(race);
                    String alignment = alignmentComboBox.getSelectedItem().toString();
                    charInfo.add(alignment);
                    String level = levelLabel.getText();
                    charInfo.add(level);
                    String experience = experienceSpinner.getValue().toString();
                    charInfo.add(experience);
                    String expNextLevel = String.valueOf(expNextLevelLabel.getText());
                    charInfo.add(expNextLevel);
                    String totalHP = String.valueOf(totalHPspinner.getValue());
                    charInfo.add(totalHP);
                    String currentHP = String.valueOf(currentHPspinner.getValue());
                    charInfo.add(currentHP);
                    String strength = String.valueOf(strengthSpinner.getValue());
                    charInfo.add(strength);
                    String dexterity = String.valueOf(dexteritySpinner.getValue());
                    charInfo.add(dexterity);
                    String constitution = String.valueOf(constitutionSpinner.getValue());
                    charInfo.add(constitution);
                    String intelligence = String.valueOf(intelligenceSpinner.getValue());
                    charInfo.add(intelligence);
                    String wisdom = String.valueOf(wisdomSpinner.getValue());
                    charInfo.add(wisdom);
                    String charisma = String.valueOf(charismaSpinner.getValue());
                    charInfo.add(charisma);
                    String armorClass = armorClassCombo.getSelectedItem().toString();
                    charInfo.add(armorClass);
                    String speed = String.valueOf(speedSpinner.getValue());
                    charInfo.add(speed);
                    String conditionOne = conditionOneCombo.getSelectedItem().toString();
                    charInfo.add(conditionOne);
                    String conditionTwo = conditionTwoCombo.getSelectedItem().toString();
                    charInfo.add(conditionTwo);
                    String conditionThree = conditionThreeCombo.getSelectedItem().toString();
                    charInfo.add(conditionThree);
                    String ID = characterID.getText();
                    charInfo.add(ID);

                    characterDB.saveCharacter(charInfo);

                    String id = characterID.getText();
                    textWriter.writeText(notesTextArea, id);

                    JOptionPane.showMessageDialog(null, "Character Updated");

                    loadCharCombo();
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Confirms that the user wants to delete the character selected in the character list combo box
                int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the selected character? This cannot be undone", "Warning", JOptionPane.YES_NO_OPTION);
                // If yes, it is removed, all fields are cleared, and the name, class, race, and alignement
                // fields are set back to editable/enabled
                if (response == JOptionPane.YES_OPTION) {
                    String selectedChar = characterSelectCombo.getSelectedItem().toString();
                    Integer ID = Integer.parseInt(selectedChar.replaceAll("\\D", ""));
                    characterDB.deleteCharacter(ID);
                    textWriter.deleteFile(String.valueOf(ID));
                    resetAllFields();
                    loadCharCombo();
                    nameTextField.setEditable(true);
                    classComboBox.setEnabled(true);
                    raceComboBox.setEnabled(true);
                    alignmentComboBox.setEnabled(true);
                }
            }
        });

        levelUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Only allows the user to level up if they have the required experience points
                // Currently a fixed value, but could be edited to increase
                // a static integer that then feeds back to the experience spinner and expToNextLevel label
                if (!experienceSpinner.getValue().equals(100)) {
                    JOptionPane.showMessageDialog(null, "You do not have the experience points necessary to level up", "Sorry!", JOptionPane.ERROR_MESSAGE);
                } else {
                    int response = JOptionPane.showConfirmDialog(null, "Confirm Level Up", "Confirm", JOptionPane.YES_NO_OPTION);
                    // If yes, the level label is increased by 1
                    // Experience spinner and expToNextLevel label are reset
                    if (response == JOptionPane.YES_OPTION) {
                        int level = ((Integer.parseInt(levelLabel.getText())) + 1);
                        levelLabel.setText(String.valueOf(level));
                        experienceSpinner.setValue(0);
                    }
                }
            }
        });

        experienceSpinner.addChangeListener(new ChangeListener() {
            @Override
            // Updates the expToNextLevel label so that it always reflects the experience points needed to level up
            // dictated by the amount of experience points they have
            public void stateChanged(ChangeEvent e) {
                int exp = Integer.parseInt(experienceSpinner.getValue().toString());
                int nextLevel = 100 - exp;
                expNextLevelLabel.setText(String.valueOf(nextLevel));
            }
        });

        currentHPspinner.addChangeListener(new ChangeListener() {
            @Override
            // Ensures that a character's current hit points cannot exceed their total hit points
            public void stateChanged(ChangeEvent e) {
                int totalHP = Integer.parseInt(totalHPspinner.getValue().toString());
                int currentHP = Integer.parseInt(currentHPspinner.getValue().toString());
                if (currentHP > totalHP) {
                    currentHPspinner.setValue(totalHP);
                }
            }
        });

        diceRollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Text area enabled to allow the functions to show the results of the rolls
                diceTextArea.setEnabled(true);
                int dieType = dieType();
                int dieCount = dieCount();
                int dieMax = dieType * dieCount;
                Random rnd = new Random();
                // rollResult ensures that the minimum/maximum roll is accurately calculated
                int rollResult = rnd.nextInt(dieMax - dieCount + 1) + dieCount;
                if (dieType == 0 || dieCount == 0) {
                    // If one of the two roller combo boxes is empty the user gets a message telling them to
                    // make sure both have a selection
                    JOptionPane.showMessageDialog(null, "Please select the type of die and number of dice you would like to roll");
                } else if (dieCount == 1) {
                    diceTextArea.append("Rolling " + diceNumberCombo.getSelectedItem().toString() + " " + dieTypeCombo.getSelectedItem().toString() + " die...\n");
                    diceTextArea.append("Result: " + rollResult + "\n");
                } else {
                    diceTextArea.append("Rolling " + diceNumberCombo.getSelectedItem().toString() + " " + dieTypeCombo.getSelectedItem().toString() + " dice...\n");
                    diceTextArea.append("Result: " + rollResult + "\n");
                }
                // The text area is disabled again so that the user cannot enter any
                // of their own text or values
                diceTextArea.setEnabled(false);
            }
        });

        clearDiceTextArea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                diceTextArea.setEnabled(true);
                diceTextArea.setText("");
                diceTextArea.setEnabled(false);
            }
        });

        saveTextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Allows the user to update their text as a secondary option to saving their character
                // Save Character button will also do this- kept in place for usability
                // The loop below prevents errors in creating a file name if the character has not been
                // saved and assigned an ID
                if (characterID.getText().isEmpty() || characterID.getText().isBlank() || characterID.getText().equals("0")) {
                    JOptionPane.showMessageDialog(null, "Save or reload your character before attempting to save text (text is saved upon saving character data)");
                } else {
                    String id = characterID.getText();
                    textWriter.writeText(notesTextArea, id);
                }
            }
        });

    }


    void frontLoader() {
        // configures the combo boxes with their correct values, configures each of the spinners
        loadCharCombo();
        loadArmorClassCombo();
        loadConditionCombos();
        loadClassCombo();
        loadAlignmentCombo();
        loadRaceCombo();
        loadDieTypeCombo();
        loadDiceNumberCombo();
        diceTextArea.setEnabled(false);

        experienceSpinner.setModel(new SpinnerNumberModel(0, 0, 100, 1));
        strengthSpinner.setModel(new SpinnerNumberModel(10, 0, 100, 1));
        dexteritySpinner.setModel(new SpinnerNumberModel(10, 0, 100, 1));
        constitutionSpinner.setModel(new SpinnerNumberModel(10, 0, 100, 1));
        intelligenceSpinner.setModel(new SpinnerNumberModel(10, 0, 100, 1));
        wisdomSpinner.setModel(new SpinnerNumberModel(10, 0, 100, 1));
        charismaSpinner.setModel(new SpinnerNumberModel(10, 0, 100, 1));
        totalHPspinner.setModel(new SpinnerNumberModel(10, 0, 100, 1));
        currentHPspinner.setModel(new SpinnerNumberModel(10, 0, 100, 1));
        speedSpinner.setModel(new SpinnerNumberModel(0, 0, 100, 1));
    }


    private void loadCharCombo() {

        characterSelectCombo.removeAllItems();

        // If the database has data, then the combo box loads the ID's and names of each character
        if (!characterDB.fetchCharacterList().isEmpty()) {
            characterSelectCombo.addItem("New Character");
            HashMap<String, String > characterList = characterDB.fetchCharacterList();
            Map<String, String> newCharList = new TreeMap<>(characterList);
            for (Map.Entry<String, String> entry : newCharList.entrySet()) {
                String charName = "ID: " + entry.getKey() + " | Name: " + entry.getValue();
                characterSelectCombo.addItem(charName);
            }
        // If the database is empty, then the combo box tells the user no characters are found
        } else {
            characterSelectCombo.addItem("New Character");
            characterSelectCombo.addItem("No Characters Found");
        }
    }

    private void loadArmorClassCombo() {

        armorClassCombo.addItem(" ");
        armorClassCombo.addItem("Light");
        armorClassCombo.addItem("Medium");
        armorClassCombo.addItem("Heavy");

    }

    private void loadConditionCombos() {

        ArrayList<String> conditions = new ArrayList<>();
        conditions.add(" "); conditions.add("Blinded"); conditions.add("Charmed"); conditions.add("Deafened"); conditions.add("Fatigued"); conditions.add("Frightened"); conditions.add("Grappled"); conditions.add("Incapacitated"); conditions.add("Invisible"); conditions.add("Paralyzed"); conditions.add("Petrified"); conditions.add("Poisoned"); conditions.add("Prone"); conditions.add("Restrained"); conditions.add("Stunned"); conditions.add("Unconscious"); conditions.add("Exhaustion");

        for (String condition : conditions) {
            conditionOneCombo.addItem(condition);
            conditionTwoCombo.addItem(condition);
            conditionThreeCombo.addItem(condition);
        }

    }

    private void loadClassCombo() {

        classComboBox.addItem(" ");
        classComboBox.addItem("Barbarian");
        classComboBox.addItem("Bard");
        classComboBox.addItem("Cleric");
        classComboBox.addItem("Druid");
        classComboBox.addItem("Fighter");
        classComboBox.addItem("Monk");
        classComboBox.addItem("Paladin");
        classComboBox.addItem("Ranger");
        classComboBox.addItem("Rogue");
        classComboBox.addItem("Sorcerer");
        classComboBox.addItem("Warlock");
        classComboBox.addItem("Wizard");

    }

    private void loadAlignmentCombo() {

        alignmentComboBox.addItem(" ");
        alignmentComboBox.addItem("Lawful Good");
        alignmentComboBox.addItem("Neutral Good");
        alignmentComboBox.addItem("Chaotic Good");
        alignmentComboBox.addItem("Lawful Neutral");
        alignmentComboBox.addItem("Neutral");
        alignmentComboBox.addItem("Chaotic Neutral");
        alignmentComboBox.addItem("Lawful Evil");
        alignmentComboBox.addItem("Neutral Evil");
        alignmentComboBox.addItem("Chaotic Evil");

    }

    private void loadRaceCombo() {

        raceComboBox.addItem(" ");
        raceComboBox.addItem("Dragonborn");
        raceComboBox.addItem("Dwarf");
        raceComboBox.addItem("Elf");
        raceComboBox.addItem("Gnome");
        raceComboBox.addItem("Half-Elf");
        raceComboBox.addItem("Halfling");
        raceComboBox.addItem("Half-Orc");
        raceComboBox.addItem("Human");
        raceComboBox.addItem("Tiefling");

    }

    private void loadDieTypeCombo() {

        dieTypeCombo.addItem(" ");
        dieTypeCombo.addItem("4-Sided");
        dieTypeCombo.addItem("6-Sided");
        dieTypeCombo.addItem("8-Sided");
        dieTypeCombo.addItem("10-Sided");
        dieTypeCombo.addItem("12-Sided");
        dieTypeCombo.addItem("20-Sided");
    }

    private void loadDiceNumberCombo() {

        diceNumberCombo.addItem(" ");
        diceNumberCombo.addItem("One");
        diceNumberCombo.addItem("Two");
        diceNumberCombo.addItem("Three");
        diceNumberCombo.addItem("Four");
        diceNumberCombo.addItem("Five");
        diceNumberCombo.addItem("Six");

    }

    private int dieCount() {
        // Functon to return an integer from the combo box selection
        String dieNumber = diceNumberCombo.getSelectedItem().toString();
        if (dieNumber.equals(" ")) {
            return 0;
        } else if (dieNumber.equals("One")) {
            return 1;
        } else if (dieNumber.equals("Two")) {
            return 2;
        } else if (dieNumber.equals("Three")) {
            return 3;
        } else if (dieNumber.equals("Four")) {
            return 4;
        } else if (dieNumber.equals("Five")) {
            return 5;
        } else if (dieNumber.equals("Six")) {
            return 6;
        } else { return 0; }
    }

    private int dieType() {
        // Function to return an integer from the combo box selection
        String dieType = dieTypeCombo.getSelectedItem().toString();
        if (dieType.equals(" ")) {
            return 0;
        } else if (dieType.equals("4-Sided")) {
            return 4;
        } else if (dieType.equals("6-Sided")) {
            return 6;
        } else if (dieType.equals("8-Sided")) {
            return 8;
        } else if (dieType.equals("10-Sided")) {
            return 10;
        } else if (dieType.equals("12-Sided")) {
            return 12;
        } else if (dieType.equals("20-Sided")) {
            return 20;
        } else { return 0; }
    }

    private void resetAllFields() {
        characterID.setText("0");
        nameTextField.setText("");
        raceComboBox.setSelectedItem(" ");
        classComboBox.setSelectedItem(" ");
        alignmentComboBox.setSelectedItem(" ");
        levelLabel.setText("01");
        experienceSpinner.setValue(0);
        expNextLevelLabel.setText("0");
        totalHPspinner.setValue(0);
        currentHPspinner.setValue(0);
        strengthSpinner.setValue(10);
        dexteritySpinner.setValue(10);
        constitutionSpinner.setValue(10);
        intelligenceSpinner.setValue(10);
        wisdomSpinner.setValue(10);
        charismaSpinner.setValue(10);
        armorClassCombo.setSelectedItem(" ");
        speedSpinner.setValue(0);
        conditionOneCombo.setSelectedItem(" ");
        conditionTwoCombo.setSelectedItem(" ");
        conditionThreeCombo.setSelectedItem(" ");
        characterID.setText("");
        notesTextArea.setText("");
    }

}

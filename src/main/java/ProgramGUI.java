import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
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
    private JSlider expNextLevelSlider;
    private JButton levelUpButton;
    private JLabel levelUpPoints;
    private JSpinner strengthSpinner;
    private JSpinner dexteritySpinner;
    private JSpinner constitutionSpinner;
    private JSpinner intelligenceSpinner;
    private JSpinner wisdomSpinner;
    private JSpinner charismaSpinner;
    private JList skillList;
    private JList miscList;
    private JList equipmentList;
    private JList itemList;
    private JSpinner totalHPspinner;
    private JSpinner currentHPspinner;
    private JComboBox armorClassCombo;
    private JSpinner speedSpinner;
    private JComboBox conditionOneCombo;
    private JComboBox conditionTwoCombo;
    private JComboBox conditionThreeCombo;
    private JComboBox dieTypeCombo;
    private JComboBox diceNumberCombo;
    private JTextArea textArea1;
    private JButton deleteSkillButton;
    private JButton addSkillButton;
    private JButton deleteMiscButton;
    private JButton addMiscButton;
    private JButton addItemButton;
    private JButton deleteItemButton;
    private JButton addEquipButton;
    private JButton deleteEquipButton;
    private JButton diceRollButton;
    private JButton saveButton;
    private JComboBox characterSelectCombo;
    private JButton loadButton;
    private JPanel characterPanel;
    private JButton deleteButton;
    private JLabel characterID;

    private CharacterDB characterDB;
    private ItemDB itemDB;
    private MiscDB miscDB;
    private SkillDB skillDB;




    public ProgramGUI(CharacterSheet program) {

        setContentPane(characterPanel);
        setTitle("D&D Character Sheet Application");
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        DefaultTableModel tableModel = new DefaultTableModel();
        experienceSpinner.setModel(new SpinnerNumberModel(0, 0, 100, 1));
        strengthSpinner.setModel(new SpinnerNumberModel(0, 0, 100, 1));
        dexteritySpinner.setModel(new SpinnerNumberModel(0, 0, 100, 1));
        constitutionSpinner.setModel(new SpinnerNumberModel(0, 0, 100, 1));
        intelligenceSpinner.setModel(new SpinnerNumberModel(0, 0, 100, 1));
        wisdomSpinner.setModel(new SpinnerNumberModel(0, 0, 100, 1));
        charismaSpinner.setModel(new SpinnerNumberModel(0, 0, 100, 1));
        totalHPspinner.setModel(new SpinnerNumberModel(0, 0, 100, 1));
        currentHPspinner.setModel(new SpinnerNumberModel(0, 0, 100, 1));
        speedSpinner.setModel(new SpinnerNumberModel(0, 0, 100, 1));

        characterDB = new CharacterDB();
        itemDB = new ItemDB();
        miscDB = new MiscDB();
        skillDB = new SkillDB();

        frontLoader();

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (characterSelectCombo.getSelectedItem().toString().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please select a character to load");
                } else if (characterSelectCombo.getSelectedItem().toString().equals("No Characters Found")) {
                    JOptionPane.showMessageDialog(null, "No Characters Found. Please create and save a new character");
                } else if (characterSelectCombo.getSelectedItem().toString().equals("New Character")) {
                   int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to create a new character? Any unsaved information will be lost", "Warning", JOptionPane.YES_NO_OPTION);
                        if (response == JOptionPane.YES_OPTION) {
                            characterID.setText("0");
                            nameTextField.setText("");
                            raceComboBox.setSelectedItem(" ");
                            classComboBox.setSelectedItem(" ");
                            alignmentComboBox.setSelectedItem(" ");
                            levelLabel.setText("00");
                            experienceSpinner.setValue(0);
                            expNextLevelSlider.setValue(0);
                            totalHPspinner.setValue(0);
                            currentHPspinner.setValue(0);
                            strengthSpinner.setValue(0);
                            dexteritySpinner.setValue(0);
                            constitutionSpinner.setValue(0);
                            intelligenceSpinner.setValue(0);
                            wisdomSpinner.setValue(0);
                            charismaSpinner.setValue(0);
                            armorClassCombo.setSelectedItem(" ");
                            speedSpinner.setValue(0);
                            conditionOneCombo.setSelectedItem(" ");
                            conditionTwoCombo.setSelectedItem(" ");
                            conditionThreeCombo.setSelectedItem(" ");
                            characterID.setText("");
                        }

                } else {
                    String selectedChar = characterSelectCombo.getSelectedItem().toString();
                    Integer ID = Integer.parseInt(selectedChar.replaceAll("\\D", ""));
                    Vector<String> charInfo = characterDB.loadCharacter(ID);

                    nameTextField.setText(charInfo.get(0));
                    nameTextField.setEditable(false);
                    classComboBox.setSelectedItem(charInfo.get(1));
                    classComboBox.setEnabled(false);
                    raceComboBox.setSelectedItem(charInfo.get(2));
                    raceComboBox.setEnabled(false);
                    alignmentComboBox.setSelectedItem(charInfo.get(3));
                    alignmentComboBox.setEnabled(false);
                    levelLabel.setText(charInfo.get(4));
                    experienceSpinner.setValue((Integer.parseInt(charInfo.get(5))));
                    expNextLevelSlider.setValue(Integer.parseInt(charInfo.get(6)));
                    totalHPspinner.setValue(Integer.parseInt(charInfo.get(7)));
                    //totalHPspinner.setEnabled(false);
                    currentHPspinner.setValue(Integer.parseInt(charInfo.get(8)));
                    strengthSpinner.setValue(Integer.parseInt(charInfo.get(9)));
                    //strengthSpinner.setEnabled(false);
                    dexteritySpinner.setValue(Integer.parseInt(charInfo.get(10)));
                    //dexteritySpinner.setEnabled(false);
                    constitutionSpinner.setValue(Integer.parseInt(charInfo.get(11)));
                    //constitutionSpinner.setEnabled(false);
                    intelligenceSpinner.setValue(Integer.parseInt(charInfo.get(12)));
                    //intelligenceSpinner.setEnabled(false);
                    wisdomSpinner.setValue(Integer.parseInt(charInfo.get(13)));
                    //wisdomSpinner.setEnabled(false);
                    charismaSpinner.setValue(Integer.parseInt(charInfo.get(14)));
                    //charismaSpinner.setEnabled(false);
                    armorClassCombo.setSelectedItem(charInfo.get(15));
                    speedSpinner.setValue(Integer.parseInt(charInfo.get(16)));
                    //speedSpinner.setEnabled(false);
                    conditionOneCombo.setSelectedItem(charInfo.get(17));
                    conditionTwoCombo.setSelectedItem(charInfo.get(18));
                    conditionThreeCombo.setSelectedItem(charInfo.get(19));
                    characterID.setText(charInfo.get(20));
                    loadSkills(ID);

                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (characterID.getText().equals("0") || characterID.getText().equals("")) {

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
                    String expNextLevel = String.valueOf(expNextLevelSlider.getValue());
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
                    JOptionPane.showMessageDialog(characterPanel, "Character Saved");

                    loadCharCombo();
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
                    String expNextLevel = String.valueOf(expNextLevelSlider.getValue());
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

                    JOptionPane.showMessageDialog(null, "Character Updated");
                    loadCharCombo();
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the selected character? This cannot be undone", "Warning", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    String selectedChar = characterSelectCombo.getSelectedItem().toString();
                    Integer ID = Integer.parseInt(selectedChar.replaceAll("\\D", ""));
                    characterDB.deleteCharacter(ID);
                    loadCharCombo();
                }
            }
        });

        levelUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (expNextLevelSlider.getValue() != 100) {
                    JOptionPane.showMessageDialog(null, "You do not have the experience points necessary to level up", "Sorry!", JOptionPane.ERROR_MESSAGE);
                } else {
                    int response = JOptionPane.showConfirmDialog(null, "Confirm Level Up", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION && expNextLevelSlider.getValue() == 100) {
                        levelUpPoints.setText("Points to Allocate: 5");
                    }
                }
            }
        });

        experienceSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int exp = Integer.parseInt(experienceSpinner.getValue().toString());
                expNextLevelSlider.setValue(exp);
            }
        });

    }



    void loadSkills(Integer ID) {
        Vector<String> skills = skillDB.loadSkills(ID);
        skillList.setListData(skills);

    }

    void frontLoader() {
        loadCharCombo();
        loadArmorClassCombo();
        loadConditionCombos();
        loadClassCombo();
        loadAlignmentCombo();
        loadRaceCombo();
        loadDieTypeCombo();
        loadDiceNumberCombo();
    }


    private void loadCharCombo() {

        characterSelectCombo.removeAllItems();
        characterSelectCombo.addItem("");

        if (!characterDB.fetchCharacterList().isEmpty()) {
            HashMap<String, String > characterList = characterDB.fetchCharacterList();
            Map<String, String> newCharList = new TreeMap<>(characterList);
            for (Map.Entry<String, String> entry : newCharList.entrySet()) {
                String charName = "ID: " + entry.getKey() + " | Name: " + entry.getValue();
                characterSelectCombo.addItem(charName);
            }
            characterSelectCombo.addItem("New Character");
        } else {
            String noChars = "No Characters Found";
            characterSelectCombo.addItem(noChars);
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



}

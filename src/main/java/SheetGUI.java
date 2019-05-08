import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SheetGUI extends JFrame {


    private JTextField charName;
    private JComboBox raceComboBox;
    private JComboBox classComboBox;
    private JComboBox alignmentComboBox;
    private JTextField charLevel;
    private JTextField charExp;
    private JTextField totalHP;
    private JTextField currentHP;
    private JPanel characterPanel;
    private JLabel nextLevelLabel;
    private JButton levelUpButton;
    private JComboBox armorComboBox;
    private JTextField speedField;
    private JComboBox statusComboOne;
    private JComboBox statusComboTwo;
    private JComboBox statusComboThree;
    private JList skillList;
    private JScrollPane skillScroller;
    private JScrollPane equipmentScroller;
    private JList equipList;
    private JScrollPane itemScroller;
    private JList itemList;
    private JComboBox modifierCombo;
    private JComboBox dieSelectCombo;
    private JComboBox diceCountCombo;
    private JButton saveCharButton;
    private JButton loadCharButton;
    private JButton addSkillButton;
    private JButton deleteSkillButton;
    private JButton addEquipmentButton;
    private JButton deleteEquipmentButton;
    private JButton addItemButton;
    private JButton deleteItemButton;
    private JButton deleteMiscButton;
    private JButton addMiscButton;
    private JComboBox comboBox1;
    private JList list1;

    public SheetGUI(CharacterSheet program) {

        setContentPane(characterPanel);
        setTitle("D&D Character Sheet Application");
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        DefaultTableModel tableModel = new DefaultTableModel();

    }



}

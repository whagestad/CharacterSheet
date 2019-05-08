public class CharacterSheet {
    public static void main(String[] args) {
        CharacterDB characterDB = new CharacterDB();
        ItemDB itemDB = new ItemDB();
        MiscDB miscDB = new MiscDB();
        EquipmentDB equipmentDB = new EquipmentDB();
        SkillDB skillDB = new SkillDB();

        characterDB.createCharacterTable();
        itemDB.createItemTable();
        equipmentDB.createEquipmentTable();
        miscDB.createMiscTable();
        skillDB.createSkillTable();

        CharacterSheet program = new CharacterSheet();
        ProgramGUI gui = new ProgramGUI(program); // Add DB

    }
}

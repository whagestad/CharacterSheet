import java.io.File;

public class CharacterSheet {
    public static void main(String[] args) {
        CharacterDB characterDB = new CharacterDB();
        TextWriter textWriter = new TextWriter();
        characterDB.createCharacterTable();
        CharacterSheet program = new CharacterSheet();
        ProgramGUI gui = new ProgramGUI(program); // Add DB

    }
}

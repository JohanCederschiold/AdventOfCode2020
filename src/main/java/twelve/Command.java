package twelve;

public enum Command {

    NORTH('N'),
    SOUTH('S'),
    EAST('E'),
    WEST('W'),
    LEFT('L'),
    RIGHT('R'),
    FORWARD('F');
    
    private char shortName;

    Command(char shortName) {
        this.shortName = shortName;
    }
}

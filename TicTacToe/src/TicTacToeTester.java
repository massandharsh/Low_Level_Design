import models.Board;
import models.BoardColor;
import reader.PropertiesReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TicTacToeTester {
    public static void main(String[] args) throws FileNotFoundException {
        PropertiesReader.readProps("players.min");
    }
}

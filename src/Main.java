import javax.imageio.IIOException;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("inputs/Tag3.txt"));

        String line;
        String previousline = "";
        Integer sum = 0;
        Integer sum2 = 0;
        //Set ist generisch. HashSet ist eine Art von Set. In diesem Fall für die Gemeinsamen Charakter.
        //Set != List weil ein Set keine Wiederholung beeinhalten kann
        //Map wäre gut, falls ich ein String und ein int (Bspw.) miteinander verknüpfen will.
        Set<String> sharedCharacters = new HashSet<>();

        while ((line = br.readLine()) != null) {
            Integer offset = line.length() / 2;
            //.substring ist ein Teil eines Strings, beginnend mit einem Startzeichen.
            //Optional kann eine Länge angegeben werden. Sonst geht es bis ans Ende des Strings
            String left = line.substring(0, offset);
            String right = line.substring(offset);
            //for ist eine art von Schleife (Zähl-Schleife), genau wie die "do-while"-Schleife.
            //Sie zählt quasi solange hoch/runter, solange eine Bedingung true ist.
            for (int i = 0; i < left.length(); i++) {
                char charAt = right.charAt(i);
                if (left.indexOf(charAt) != -1) {
                    // a += b ist die kurze Form von a = a+b
                    sum += getCharacterValue(charAt);
                    //break kann man wie bei Switch auch für alle anderen Schleifen bzw Prüfungen benutzen.
                    break;
                }
            }
            /*
             *         | previousLine | sharedCharacters
             * Zeile 1 | leer         | leer
             * Zeile 2 | gefüllt      | leer
             * Zeile 3 | gefüllt      | gefüllt
             *
             */
            if (previousline.equals("")) {
                //".equals benutzt man für Strings anstatt ==
                previousline = line;

            } else {

                if (sharedCharacters.size() > 0) {
                    //for-each Schleife benutzt man um alle Inhalte einer collection (mehrere Werte) zu durchlaufen.
                    for (String character2 : sharedCharacters) {
                        //.contains prüft, ob ein belieber String in einem anderen String vorkommt.
                        //In diesem Fall, benutzt man.contains, weil es sich um einen String handelt.
                        if (line.contains(character2)) {
                            sum2 += getCharacterValue(character2.charAt(0));
                        }
                        //previousline=""; Um die Werte zurückzusetzen um wieder in den nächsten 3 Zeilen bei Null anzufangen.
                        previousline = "";
                        sharedCharacters = new HashSet<>();
                    }
                } else {
                    for (int i = 0; i < previousline.length(); i++) {
                        //char speichert ein 8-bit Integer die einem ASCII-Zeichen entsprechen können.
                        char charAt = previousline.charAt(i);
                        //.indexOf() sagt einem ob und wenn ja wo sich ein Zeichen in einem String befindet
                        //In diesem Fall benutzt man .indexOf() weil es sich um einen char handelt
                        if (line.indexOf(charAt) != -1) {
                            sharedCharacters.add(String.valueOf(charAt));
                        }
                    }
                }
            }
        }
        System.out.println(sum);
        System.out.println(sum2);
    }

    private static int getCharacterValue(char character) {

        //Eine Funktion ist das Auslagern von Code in ein Unter-Programm um die verdoppelung von Code zu vermeiden.
        //In diesem Fall, weil es sich im Main nicht ändert sich die Berechnung der Zeichenwerte nicht. Sie bleibt gleich.

        if (character >= 97) {
            return character - 96;
        }
        return character - 38;
    }
}
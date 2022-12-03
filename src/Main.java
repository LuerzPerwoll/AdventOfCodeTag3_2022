import javax.imageio.IIOException;
import java.io.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("inputs/Tag3.txt"));
        String line;
        Integer sum = 0;
        while ((line = br.readLine()) != null) {
            Integer offset = line.length()/2;
            String left = line.substring(0, offset);
            String right = line.substring(offset);

            for(int i = 0; i < left.length(); i++ ) {
                char charAt = right.charAt(i);
                if (left.indexOf(charAt)!=-1) {
                    sum += getCharacterValue(charAt);
                    break;
                  // -38 upper case - 96 lower case
                }
            }
        }
        System.out.println(sum);
    }
    private static int getCharacterValue(char character){
        if (character >= 97){
        return character -  96;
        }
        return character - 38;
    }
}
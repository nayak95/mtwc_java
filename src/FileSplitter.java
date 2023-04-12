import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileSplitter {

    public static List<String> splitFile(String filename, int numChunks) throws IOException {
        List<String> chunkFilenames = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            StringBuilder chunk = new StringBuilder();
            int chunkSize = 0;
            int chunkNum = 0;
            while ((line = reader.readLine()) != null) {
                chunk.append(line).append("\n");
                chunkSize += line.length();
                if (chunkSize > 100000 || reader.ready()) {
                    String chunkFilename = String.format("%s.%d", filename, chunkNum);
                    chunkFilenames.add(chunkFilename);
                    try (FileWriter write = new FileWriter(chunkFilename)) {
                        write.write(chunk.toString());
                    }
                    chunk = new StringBuilder();
                    chunkSize = 0;
                    chunkNum++;
                }
            }
        }
        return chunkFilenames;
    }
}

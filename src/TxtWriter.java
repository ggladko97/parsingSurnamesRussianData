import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ggladko97 on 04.11.16.
 */
public class TxtWriter {
    private String filePath;
    private List<String>list=new ArrayList<>();
    private FileWriter fileWriter;
    private File fileOut;
    public TxtWriter(String filePath, List<String>list) throws IOException
    {
        this.filePath=filePath;
        this.list=list;
        fileOut = new File(filePath);
        fileWriter = new FileWriter(fileOut);

    }
    public void writeFile() throws IOException {
        fileWriter.write(String.valueOf(list));
        System.out.println("File has been written succesfully!");
    }


}

import java.io.IOException;
import java.util.*;

/**
 * Created by ggladko97 on 03.11.16.
 */
public class ParceFromTxt {
    private static String resultStringFromTxt=null;
    private  static List<String> listOfSurnames = new ArrayList<>();
    private  static List<String> listOfSurnamesFull = new ArrayList<>();
    private static List<String> listOfSurnamesExcel = new ArrayList<>();
    public static void main(String[] args) {
        long a=System.currentTimeMillis();
        long c = 0;
        TxtParser parser = new TxtParser();
        try {
            ExcelParser excelParser = new ExcelParser("/home/ggladko97/Downloads/spiski_harkovchan.xls");
            listOfSurnamesExcel=excelParser.extractRow();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Problem with xls file path or with row extraction");
        }

        try {
            resultStringFromTxt=parser.parse("/home/ggladko97/Downloads/All_Moscow_1910.txt");

            //[^А-Я]{2,25}[\-]?[^А-Я][\w]?[^А-Я][^А-Я]{1,10}

           String[] substrings1 = resultStringFromTxt.split("[^А-Я]{2,25}[\\-]?[^А-Я]{2,20}");

            parsingSurnames(substrings1);
            int positionCounter;
            for(String s:listOfSurnames){
                if(s.contains(" ")){
                    positionCounter=s.indexOf(" ");
                    int startIndex = positionCounter;
                    int endIndex = s.length();;
                    String replacement = "";
                    String replacedPart = s.substring(startIndex, endIndex);
                    s = s.replace(replacedPart, replacement);
                    if(s.contains("І")){
                        s.replace("І","И");

                    }
                    if(s.contains("Ъ")){
                        s.replace("Ъ","");
                    }
                    if(s.contains("-")){
                        s.replace("-","");
                    }
                    listOfSurnamesFull.add(s);
                }
                else{

                    listOfSurnamesFull.add(s);
                }



            }
            long b = System.currentTimeMillis();
            System.out.println("Time parsing(ms): "+(b-a));
            try {
                TxtWriter writer = new TxtWriter("/home/ggladko97/Desktop/newResult.txt",listOfSurnamesFull);
                writer.writeFile();
                c = System.currentTimeMillis();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Time writing to file(ms): "+(c-b));
            System.out.println("Quantity of surnames parsed: "+listOfSurnamesFull.size());
//            toLowerCase(listOfSurnamesFull);
//            toLowerCase(listOfSurnamesExcel);
            System.out.println("Kharkov list: "+listOfSurnamesExcel);
            System.out.println("Occurenceces of Kharkov in txt: "+
                    comparingTwoDataSets(toLowerCase(listOfSurnamesFull),toLowerCase(listOfSurnamesExcel)));


        } catch (IOException e) {
            e.printStackTrace();

        }
        //// TODO: 03.11.16 : kurwa mać zrobić jebany defiz
    }

    private static void parsingSurnames(String[] substrings) {
        for(String s:substrings){

            if(s.length() > 2 && Character.isUpperCase(s.charAt(0))&& Character.isUpperCase(s.charAt(1))||s.length() > 2
                    && Character.isUpperCase(s.charAt(0))&& Character.isUpperCase(s.charAt(1))&&s.contains(" - "))
            {
                listOfSurnames.add(s);

            }
        }
    }
    private static int comparingTwoDataSets(List<String>listExcel,List<String>listTxt){
        int count =0;
        for (String a : listTxt)
        {
            for (String b : listExcel)
            {
                if (a.equals(b))
                {
                    count+=1;
                    break;
                }
            }
        }
        
        return count;
    }
    private static List<String> toLowerCase(List<String> strings)
    {
        ListIterator<String> iterator = strings.listIterator();
        while (iterator.hasNext())
        {
            iterator.set(iterator.next().toLowerCase());
        }
        return strings;
    }
}

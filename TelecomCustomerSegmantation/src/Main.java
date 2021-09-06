import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    ArrayList<String>[] inputValue = new ArrayList[7044];
    ArrayList<String>[] selectedValue = new ArrayList[7044];
    ArrayList<String> telecomsDataList = new ArrayList();
    String[] arrOfStr;
    String string, a =" ";

    public static void main(String[] args) throws IOException {
        Main t= new Main();
        t.getInput();
    }

    public void getInput() throws IOException, NumberFormatException
    {
        for (int i = 0; i < 7044; i++) {
            inputValue[i] = new ArrayList<String>();
            selectedValue[i] = new ArrayList<String>();
        }

        File f = new File("data/data.csv");
        Scanner input = new Scanner(f);

        while (input.hasNextLine())
        {
            string = input.nextLine();
            telecomsDataList.add(string);
        }


        selectedValue[0] = new ArrayList<>(List.of("customerID","Tenure", "PhoneService","Streaming","MonthlyCharge"));

        for (int j = 1; j < telecomsDataList.size(); j++){
            arrOfStr = telecomsDataList.get(j).split(",", 21);

            if(arrOfStr[6].equals("Yes"))
                arrOfStr[6]="1";
            else
                arrOfStr[6]="0";

            if(arrOfStr[13].equals("Yes"))
                arrOfStr[13]="1";
            else
                arrOfStr[13]="0";

            selectedValue[j].add(arrOfStr[0]);
            selectedValue[j].add(arrOfStr[5]);
            selectedValue[j].add(arrOfStr[6]);
            selectedValue[j].add(arrOfStr[13]);
            selectedValue[j].add(arrOfStr[18]);

        }

        AlgorithmKMean k = new AlgorithmKMean(selectedValue);
        k.calculateData();

    }

}

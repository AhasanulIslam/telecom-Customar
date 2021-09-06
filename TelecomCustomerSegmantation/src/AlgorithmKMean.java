import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class AlgorithmKMean {

    ArrayList<String>[] selectedInputDataList = new ArrayList[2450];
    ArrayList<ArrayList<String>> group1 = new ArrayList();
    ArrayList<ArrayList<String>> group2 = new ArrayList();
    ArrayList<ArrayList<String>> group3 = new ArrayList();
    Random rand = new Random();
    double eps=0.01;
    UpdateValue updateCentroid = new UpdateValue();
    ArrayList<String>[] randomList = new ArrayList[4],tempCentroidValue = new ArrayList[3],randomList2 = new ArrayList[4];
    int c1=0,c2=0,c3=0,c4=0;
    double[] tSum = new double[4];
    double[] tSum2 = new double[4];
    double[] tSum3 = new double[4];


    public AlgorithmKMean(ArrayList<String>[] selectedInputDataList){
        this.selectedInputDataList = selectedInputDataList;
    }

    public void calculateData() throws IOException {
        int dataLength = selectedInputDataList.length, checker=0;


        for(int i=0;i<4;i++)
        {
            tSum[i]=0;
            tSum2[i]=0;
            tSum3[i]=0;
        }

        randomList[0]=selectedInputDataList[0];
        randomList2[0]=selectedInputDataList[0];
        for (int i=1;i<randomList.length;i++){
            int randomIndex= rand.nextInt(dataLength);
            if(randomIndex==0)
                randomIndex=1;
            randomList[i]= selectedInputDataList[randomIndex];
            randomList2[i]= selectedInputDataList[randomIndex];


        }

        while(true){
            group1.clear();
            group2.clear();
            group3.clear();
            for(int j=1; j<selectedInputDataList.length;j++){
                double minimumDistance = getMinimumValue(
                        distanceCalculator(randomList[1],selectedInputDataList[j]),
                        distanceCalculator(randomList[2],selectedInputDataList[j]),
                        distanceCalculator(randomList[3],selectedInputDataList[j])
                );

                if(minimumDistance== distanceCalculator(randomList[1],selectedInputDataList[j]))
                {
                    tSum = updateCentroid.addSum(selectedInputDataList[j],tSum);
                    c1++;
                    group1.add(selectedInputDataList[j]);
                }

                else if(minimumDistance== distanceCalculator(randomList[2],selectedInputDataList[j]))
                {
                    tSum2 = updateCentroid.addSum(selectedInputDataList[j],tSum2);
                    c2++;
                    group2.add(selectedInputDataList[j]);
                }

                else if(minimumDistance== distanceCalculator(randomList[3],selectedInputDataList[j]))
                {
                    tSum3 = updateCentroid.addSum(selectedInputDataList[j],tSum3);
                    c3++;
                    group3.add(selectedInputDataList[j]);
                }

            }

            for(int m=0;m<randomList.length-1;m++){
                tempCentroidValue[m]=randomList[m+1];
            }


            randomList[1]=updateCentroid.averageCalculator(tSum,c1,randomList[1]);
            randomList[2]=updateCentroid.averageCalculator(tSum2,c2, randomList[2]);
            randomList[3]=updateCentroid.averageCalculator(tSum3,c3, randomList[3]);

            if(distanceCalculator(randomList[1],tempCentroidValue[0])<eps && distanceCalculator(randomList[2],tempCentroidValue[1])<eps
                    && distanceCalculator(randomList[3],tempCentroidValue[2])<eps){
                break;
            }

            checker++;

        }


        for(ArrayList<String> s:randomList2 ){
            System.out.println("RandomData: "+s);
        }

        for(ArrayList<String> s:randomList ){
            System.out.println("UpdatedData: "+s);
        }

        for(ArrayList<String> s: group1){
            System.out.println("Group1: "+s);
        }

        for(int i=0;i<10;i++){
            System.out.println("----------------------------------------------------------------------------");
        }

        for(ArrayList<String> s: group2){
            System.out.println("Group2: "+s);
        }


        for(int i=0;i<10;i++){
            System.out.println("----------------------------------------------------------------------------");
        }

        for(ArrayList<String> s: group3){
            System.out.println("Group3: "+s);
        }
        
    }


    public double getMinimumValue(double d1, double d2, double d3)
    {
        double numbers[]={d1,d2,d3};

        double minValue = numbers[0];
        for(int i=1;i<numbers.length;i++){
            if(numbers[i] < minValue){
                minValue = numbers[i];
            }
        }
        return minValue;
    }

    public double distanceCalculator(ArrayList<String> randomData, ArrayList<String> selectedData)
    {
        double d=0;
        
        d= Math.sqrt(  differenceCalculator(Double.parseDouble(randomData.get(1)),Double.parseDouble(selectedData.get(1))) +
                differenceCalculator(Double.parseDouble(randomData.get(2)),Double.parseDouble(selectedData.get(2)))
                + differenceCalculator(Double.parseDouble(randomData.get(3)),Double.parseDouble(selectedData.get(3))) +
                differenceCalculator(Double.parseDouble(randomData.get(4)),Double.parseDouble(selectedData.get(4))));
        return  d;
    }

    public double differenceCalculator(double x, double y)
    {
        double distance = 0;

        distance=(x-y)*(x-y);

        return  distance;
    }

}

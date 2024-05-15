import java.util.ArrayList;
public class Calculator {
    public Double divide(double num1,double num2){
        if(num2==0.0){
          return null;
        }
        return num1/num2;
      }
      public Double calc_average(ArrayList<Integer> array){
        double sum=0.0;
        int counter=0;
        for(int i=0;i<array.size();i++){
            if(!array.get(i).equals(0)){
                sum+=array.get(i);
                counter++;
            }
        }
        return divide(sum, counter);
      }
      public Double calcStandardDeviation(ArrayList<Integer> array){
        Double mean=calc_average(array);
        double deviation=0;
        int counter=0;
        for(int i=0;i<array.size();i++){
            if(!array.get(i).equals(0)){
                deviation+=Math.pow(array.get(i)-mean,2);
                counter++;
            }
        }
        if(divide(deviation,(counter-1))!=null){
          deviation=Math.sqrt(divide(deviation,(counter-1)));
        }
        else{
          System.err.println("Error: Invalid number of songs, each user must rate 2 or more songs");
          return null;
        }
        return deviation;
      }
      public double calcEuclidianDistance(ArrayList<Double> songA,ArrayList<Double> songB){
        double sum=0;
        for(int i=0;i<songA.size();i++){
            sum+=Math.pow(songA.get(i) - songB.get(i),2);
        }
        return Math.sqrt(sum);
    }
}

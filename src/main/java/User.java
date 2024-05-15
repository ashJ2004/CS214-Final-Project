import java.util.ArrayList;
public class User extends Calculator{
    ArrayList<Integer> ratings;
    ArrayList<Double> normalized;
    ArrayList<Double> predictedRatings;
    Double mean;
    Double stdev;
    public User(){
        ratings=new ArrayList<Integer>();
        normalized=new ArrayList<Double>();
        predictedRatings=new ArrayList<Double>();
    }
    public User(ArrayList<Integer> rating){
        ratings=rating;
        normalized=new ArrayList<Double>();
        predictedRatings=new ArrayList<Double>();
    }
    public User(ArrayList<Integer> rating,ArrayList<Double> norms){
        ratings=rating;
        normalized=norms;
        predictedRatings=new ArrayList<Double>();
    }
    public void addRating(Integer rating){
        ratings.add(rating);
    }
    public boolean isValidUser(){
        if(ratings.size()==0){
            return false;
        }
        else{
            Integer repititionCheck=ratings.get(0);
            for(int i=0;i<ratings.size();i++){
                if(!ratings.get(i).equals(0) && repititionCheck==0){
                    repititionCheck=ratings.get(i);
                }
                if(!repititionCheck.equals(ratings.get(i)) && !ratings.get(i).equals(0)){
                    return true;
                }
            }
        }
        return false;
    }
    public void normalizeRatings(){
        normalized.clear();
        mean=calc_average(ratings);
        stdev=calcStandardDeviation(ratings);
        //calculate each normalized array entry using mean and stdev and ratings list
        for(int i=0;i<ratings.size();i++){
            if(!ratings.get(i).equals(0)){
                normalized.add((ratings.get(i)-mean)/stdev);
            }
            else{
                //sets normalized value to 10 for the normalized ratings set, as 10 will never naturally occur in normalized values,
                //but zero will
                normalized.add(0.0);
            }
        }
    }
}


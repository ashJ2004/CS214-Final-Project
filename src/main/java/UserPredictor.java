import java.io.IOException;
import java.util.ArrayList;
public class UserPredictor {
    private ArrayList<User> users;
    private UserMatrix userMatrix;
    public UserPredictor(ArrayList<User> users){
        this.users=users;
        validizeUsers();
        normalizeUsers();
        try{
            userMatrix=new UserMatrix(users);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public void runPredictions(){
        for(int i=0;i<users.size();i++){
            for(int j=0;j<users.get(i).ratings.size();j++){
                if(users.get(i).ratings.get(j)==0){
                    int index=findSimilarUser(i,j);
                    users.get(i).predictedRatings.add(users.get(index).normalized.get(j));
                }
                else{
                    users.get(i).predictedRatings.add(users.get(i).normalized.get(j));
                }
            }
        }
    }
    private int findSimilarUser(int userIndex,int songIndex){
        //need to manage if the found user does not have a rating for the song, can be solved by passing song index(j) into this function
        int foundUser;
        double mostSimilar;
        if(userIndex==userMatrix.size()-1){
            mostSimilar=userMatrix.getUserSimilarity(userIndex,0);
            foundUser=0;
        }
        else{
            mostSimilar=userMatrix.getUserSimilarity(userIndex,userMatrix.size()-1);
            foundUser=userMatrix.size()-1;
        }
        for(int i=0;i<userMatrix.size();i++){
            if(i!=userIndex && users.get(i).ratings.get(songIndex)!=0){
                if(userMatrix.getUserSimilarity(userIndex,i)>mostSimilar){
                        foundUser=i;
                        mostSimilar=userMatrix.getUserSimilarity(userIndex, i);
                }
            }
        }
        return foundUser;
    }
    private int calcPrediction(int user1,int user2,int songIndex){
        double prediction;
        prediction=(users.get(user2).normalized.get(songIndex) * users.get(user1).stdev) + users.get(user1).mean;
        int rounded=(int)Math.round(prediction);//issue with rounding values, etiher too high or too low
        if(rounded>5){
            rounded=5;
        }
        return rounded;
    }
    private void validizeUsers(){
        for(int i=0;i<users.size();i++){
            if(!users.get(i).isValidUser()){
              users.remove(users.get(i));
            }
        }
    }
    private void normalizeUsers(){
        for(User user:users){
            user.normalizeRatings();
        }
    }
}

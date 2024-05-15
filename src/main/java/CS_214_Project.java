import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CS_214_Project {
    private static FileReader fileReader=new FileReader();
    private static UserPredictor predictions;
    private static FileOuput output=new FileOuput();
    private static ArrayList<User> users;
    private static ArrayList<Song> songs;
    private static Clusters kmeans=new Clusters();
    private static BestSongs reccomendations;
  public static void main(String[] args) {
    if(args.length<5){
      System.err.println("Missing inputs, please input the song and rating files, desired output file, and k AND n values (5 total)");
    }
     try{
      users=fileReader.readRatingFile(args[1]);
      songs=fileReader.readNameFile(args[0]);
    }catch(FileNotFoundException e){
      System.err.println("Error: invalid File Name, please make sure the directory and name(s) are correct");
    }
    if(users!=null){
      if(users.get(0).ratings.size()!=songs.size()){
        System.err.println("Error: Number of songs do not match number of ratings for each user");
      }
      else{
        predictions=new UserPredictor(users);
        predictions.runPredictions();
        addNormalized();
        if(validInputs(args[3], args[4])){
          kmeans.kMeans(Integer.parseInt(args[3]),songs);
          int bestSong=findHighestRated(users.get(Integer.parseInt(args[4])-1));
          reccomendations=new BestSongs(kmeans.clusterCenters,songs.get(bestSong));
          if(reccomendations.residentCluster.center.size()==0){
            System.err.println("Best song: \'"+songs.get(bestSong)+"\' not found, please check input file for its existence");
          }
          else{
            reccomendations.calcBestSongs();
            output.writeOutput(args[2],reccomendations.songRanking);
          }
        }
      }
  }
  }
  public static void addNormalized(){
    for(int i=0;i<songs.size();i++){
      for(User user:users){
        songs.get(i).normalizedRatings.add(user.predictedRatings.get(i));
      }
    }
  }
  public static int findHighestRated(User user){
    int maxIndex=0;
    int maxRating=user.ratings.get(0);
    for(int i=0;i<user.ratings.size();i++){
        if(user.ratings.get(i)>maxRating){
            maxIndex=i;
            maxRating=user.ratings.get(i);
        }
        else if(user.ratings.get(i)==maxRating){
            maxIndex=tieBreaker(maxIndex,i);
            maxRating=user.ratings.get(maxIndex);
        }
    }
    return maxIndex;
}
public static int tieBreaker(int index1,int index2){
  double sum1=0;
  double sum2=0;
  for(int i=0;i<users.size();i++){
    sum1+=songs.get(index1).normalizedRatings.get(i);
    sum2+=songs.get(index2).normalizedRatings.get(i);
  }
  if(sum1>sum2){
    return index1;
  }
  return index2;
}
  public static boolean validInputs(String k, String n){
    if((k.charAt(0)>'9' || k.charAt(0)<'0')  || k.contains(".")){
      System.err.println("Error: Invalid k value, must be a whole integer with no decimal");
      return false;
    }
    else{
      if(n.contains(".")){
        System.err.println("Error: Invalid n value, must be a whole integer with no decimal");
        return false;
      }
      for(int i=0;i<n.length();i++){
        if(n.charAt(i)>'9' || n.charAt(i)<'0'){
          System.err.println("Error: Invalid n value, must be a whole integer with no decimal");
          return false;
        }
      }
      if(Integer.parseInt(n)>users.size()){
        System.err.println("Error: User access index "+n+" exceeds user count of " +users.size());
        return false;
      }
      return true;
    }
  }
}

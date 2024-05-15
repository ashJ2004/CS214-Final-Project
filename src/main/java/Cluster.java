import java.util.ArrayList;
public class Cluster {
    //Singular cluster will hold an arraylist of songs
    //has an arralist for the average user
    //has function to calc the average user
    ArrayList<Song> songs=new ArrayList<Song>();
    ArrayList<Double> center;
    public Cluster(){
        center=new ArrayList<Double>();
    }
    public Cluster(String name, ArrayList<Double> normals){
        addSong(new Song(name, normals));
        center=normals;
    }
    public void addSong(Song song){
        songs.add(song);
    }
    public void averageUser(){
        ArrayList<Double> averageRatings=new ArrayList<Double>();
        for(int i=0;i<songs.get(0).normalizedRatings.size();i++){
            double sum=0;
            for(int j=0; j<songs.size();j++){
                sum+=songs.get(j).getRating(i);
            }
            averageRatings.add(sum/songs.size());
        }
        center=averageRatings;
    }
    public void clearSongs(){
        songs.clear();
    }
 }

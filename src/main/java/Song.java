import java.util.ArrayList;
public class Song {
    String name;
    double distanceToBestSong;
    ArrayList<Double> normalizedRatings;
    public Song(String name){
        this.name=name;
        normalizedRatings=new ArrayList<Double>();
    }
    public Song(String name, ArrayList<Double> norms){
        this.name=name;
        normalizedRatings=norms;
    }
    public Double getRating(int index){
        return normalizedRatings.get(index);
    }
    public boolean equals(Song song){
        return this.name.equals(song.name);
    }
}

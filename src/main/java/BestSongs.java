import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
public class BestSongs extends Calculator{
    Song song;
    Cluster residentCluster;
    LinkedList<Song> songRanking=new LinkedList<Song>();
    public BestSongs(ArrayList<Cluster> clusters, Song song){
        this.song=song;
        this.residentCluster=findResidentCluster(song, clusters);
    }
    public Cluster findResidentCluster(Song song, ArrayList<Cluster> clusters){
        for(Cluster c:clusters){
            for(Song s:c.songs){
                if(s.equals(song)){
                    return c;
                }
            }
        }
        return new Cluster();
    }
    public void calcBestSongs(){
        for(Song s:residentCluster.songs){
            if(!s.equals(song)){
                s.distanceToBestSong=calcEuclidianDistance(s.normalizedRatings,song.normalizedRatings);
                findIndex(s);
            }
        }
    }
    public void findIndex(Song newSong){
        if(songRanking.size()==0){
            songRanking.add(newSong);
        }
        else{
            boolean foundIndex=false;
            for(Song s:songRanking){
                if(newSong.distanceToBestSong<s.distanceToBestSong){
                    songRanking.add(songRanking.indexOf(s),newSong);
                    foundIndex=true;
                    break;
                }
            }
            if(!foundIndex){
                songRanking.addLast(newSong);
            }
        }
    }
}

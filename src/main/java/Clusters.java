import java.util.ArrayList;

public class Clusters {
    //Start with first k songs to create clusters, then use below methods to find clusters and cluster centers
    //modify AverageUser to be in cluster.java, and k means to take an arraylist of cluster objects
    ArrayList<Cluster> clusterCenters=new ArrayList<Cluster>();
    Calculator calc=new Calculator();
    public ArrayList<Cluster> kMeans(int k, ArrayList<Song> songs){
        clusterCenters.clear();
        for(int i=0;i<k;i++){
            clusterCenters.add(new Cluster(songs.get(i).name, songs.get(i).normalizedRatings));
        }
        for(int j=0;j<10;j++){
            for(Cluster c:clusterCenters){
                c.clearSongs();
            }
            for(int i=0;i<songs.size();i++){
                int index=findSimilar(songs.get(i));
                clusterCenters.get(index).addSong(songs.get(i));
            }
            for(Cluster c:clusterCenters){
                c.averageUser();
            }
        }
        return clusterCenters;
    }
    private int findSimilar(Song song){
        double clusterAverage=calc.calcEuclidianDistance(song.normalizedRatings,clusterCenters.get(0).center);
        int clusterIndex=0;
        for(int i=0;i<clusterCenters.size();i++){
            double currDistance=calc.calcEuclidianDistance(song.normalizedRatings, clusterCenters.get(i).center);
            if(currDistance<clusterAverage){
                clusterAverage=currDistance;
                clusterIndex=i;
            }
        }
        return clusterIndex;
    }
}

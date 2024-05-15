import java.util.LinkedList;
import java.io.FileWriter;
import java.io.IOException;
public class FileOuput {
    public void writeOutput(String file,LinkedList<Song> songs){
        //Takes arg for nth cluster, goes through cluser list, and prints all the names of the songs
        try{
            FileWriter writer=new FileWriter(file);
            if(songs.size()==0){
                writer.write("No similar songs located within cluster\n");
            }
            else if(songs.size()<20){
                for(int i=0;i<songs.size();i++){
                    writer.write(songs.get(i).name + "\n");
                }
            }
            else{
                for(int i=0;i<20;i++){
                    writer.write(songs.get(i).name + "\n");
                }
            }
            writer.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}

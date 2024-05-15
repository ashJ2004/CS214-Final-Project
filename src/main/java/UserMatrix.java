import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class UserMatrix {
    /*POSSIBLE IMPROVEMENT: convert MatrixWriter to be a class constructer, store a userlist, as main runs, list is added to,
    or list can be instantiated, goes on adding until command to output is called. already done using fileReader, but could be 
    transfered by creating an instance in main instantiating the given userlist from FileReader into this class.
    */
    private double[][] matrix;
    public UserMatrix(int userCount){
        matrix=new double[userCount][userCount];
    }
    public UserMatrix(ArrayList<User> userList)throws IOException{
        matrix=new double[userList.size()][userList.size()];
        writeUserSimilarity(userList);
    }
    public void writeUserSimilarity(ArrayList<User> userList)throws IOException{
        for(int i=0;i<userList.size();i++){
            for(int j=0;j<userList.size();j++){
                double sum=0;
                
                for(int k=0;k<userList.get(0).normalized.size();k++){
                    sum+=userList.get(i).normalized.get(k)*userList.get(j).normalized.get(k);
                }
                matrix[i][j]=sum;                
            }
        }
    }
    public double getUserSimilarity(int index){
        return matrix[index][index];
    }
    public double getUserSimilarity(int index1,int index2){
        return matrix[index1][index2];
    }
    public int size(){
        return matrix.length;
    }
    public void outputMatrix(String file){
        try{
            FileWriter writer = new FileWriter(file);
            for(int i=0;i<matrix.length;i++){
                for(int j=0;j<matrix[i].length;j++){
                    writer.write(matrix[i][j]+" ");
                }
                writer.write("\n");
            }
            writer.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}

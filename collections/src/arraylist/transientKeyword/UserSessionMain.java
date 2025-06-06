package arraylist.transientKeyword;

import java.io.*;

public class UserSessionMain {
    public static void main(String[] args) throws IOException {
        String filename="src/arraylist/transientKeyword/session.txt";

        UserSession userSession=new UserSession("Someswari","Somi@2411");

        try(ObjectOutputStream outputStream=new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(userSession);
            System.out.println("object serialized");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try(ObjectInputStream inputStream=new ObjectInputStream(new FileInputStream(filename))){
            UserSession deserialize= (UserSession) inputStream.readObject();
            System.out.println("deserialized object");
            System.out.println(deserialize.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

package fileEncryptDecrypt;

public class XORCipher {
    public static String xorEncryptDecrypt(String text,int key){
        StringBuilder result=new StringBuilder();
        for (char ch:text.toCharArray()){
            result.append((char)(ch ^ key));
        }
        return result.toString();
    }
}

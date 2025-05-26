package fileEncryptDecrypt;

public class CaesarCipher {
    public static String ceaserEncrypt(String text,int shift){
        StringBuilder result=new StringBuilder();
        for (char ch:text.toCharArray()){
            if(ch>=32 && ch<=126){
                char encryptedText= (char) (((ch-32+shift)%95)+32);
                result.append(encryptedText);
            }
            else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static String ceaserDecrypt(String text,int shift){
        StringBuilder result=new StringBuilder();
        for (char ch:text.toCharArray()){
            if(ch>=32 && ch<=126){
                char encryptedText= (char) (((ch-32-shift+95)%95)+32);
                result.append(encryptedText);
            }
            else {
                result.append(ch);
            }
        }
        return result.toString();
    }
}

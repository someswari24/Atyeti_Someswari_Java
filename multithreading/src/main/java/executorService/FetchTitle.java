package executorService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

public class FetchTitle implements Callable<String> {
    private String urlString;

    public FetchTitle(String urlString) {
        this.urlString = urlString;
    }

    @Override
    public String call() throws Exception {
        try {
            URL url=new URL(urlString);
            URLConnection urlConnection=url.openConnection();
            urlConnection.setConnectTimeout(1000);

            try(BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))){
                String line;
                StringBuilder stringBuilder=new StringBuilder();

                while ((line=bufferedReader.readLine())!=null){
                    stringBuilder.append(line);

                }
                return  fetchingTitle(stringBuilder.toString());
            }
           
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String fetchingTitle(String data) {
        int start=data.indexOf("<title>");
        int end=data.indexOf("</title>");
        if (start!=-1 && end !=-1 && start<end){
            return data.substring(start+7,end);
        }
        return "No Title found";
    }
}

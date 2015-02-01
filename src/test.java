import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class test {
  public static void main(String[] args) {
    if(args.length<2){
    	System.out.println("Arguments are invalid.");

    	return;
    }
    Runnable d = new DownloadData(args[0],args[1]);
    Thread t = new Thread(d);
    t.start();
  }

  public static void doneLoadingDisplay(String text,String fileName) {
    System.out.print(text);
    try {
      FileOutputStream os = new FileOutputStream(fileName);
      os.write(text.getBytes());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
class DownloadData implements Runnable {
  String fileName;
  public DownloadData(String url,String fileName) {
    this.fileName=fileName;
    try {
      this.url = new URL(url);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }

  URL url;
  InputStream is = null;
  BufferedReader br;

  public void run() {

    try {
      is = url.openStream();
      br = new BufferedReader(new InputStreamReader(is));
      test.doneLoadingDisplay(br.readLine(),fileName);
      is.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}

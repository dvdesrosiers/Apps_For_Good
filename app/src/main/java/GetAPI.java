import java.io.*;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class GetAPI{
    public static void main(String args[]){
        String url="https://data.trilliumtransit.com/gtfs/wrta-ma-us/wrta-ma-us.zip";
        String content = readZipFileFromRemote(url);
        System.out.println(content);
    }

    public static String readZipFileFromRemote(String remoteFileUrl) {

        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(remoteFileUrl);
            InputStream in = new BufferedInputStream(url.openStream(), 1024);
            ZipInputStream stream = new ZipInputStream(in);
            byte[] buffer = new byte[1024];
            ZipEntry entry;
            while ((entry = stream.getNextEntry()) != null) {
                int read;
                while ((read = stream.read(buffer, 0, 1024)) >= 0) {
                    sb.append(new String(buffer, 0, read));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}

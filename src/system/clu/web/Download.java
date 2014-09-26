package system.clu.web;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Download
{
	// clu-project.esy.es
	public static void downloadFile(String _url)
	{
		URL url;
		URLConnection connection;
		InputStream input = null;
		FileOutputStream writeFile = null;
		try
		{
			url = new URL(_url);
			connection = url.openConnection();
			input = connection.getInputStream();
			String fileName = url.getFile().substring(url.getFile().lastIndexOf('/') + 1);
			writeFile = new FileOutputStream("assets/" + fileName);
			byte[] buffer = new byte[4096];
            int read;
			while ((read = input.read(buffer)) > 0)
		        writeFile.write(buffer, 0, read);
			writeFile.flush();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
            {
                writeFile.close();
                input.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
		}
	}
}

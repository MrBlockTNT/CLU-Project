package system.clu.main;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import system.clu.crash.CrashReport;
import system.clu.misc.Save;
import system.clu.recognize.Recognizer;
import system.clu.web.Download;

public class CLU
{
	/*
	 * Runtime et processus (start/stop)
	 */
	
	/*
	 * CrashReport.generateFile();
	   try
	   {
	   e.printStackTrace(new PrintStream(CrashReport.fileName));
	   } catch (FileNotFoundException e1)
	   {
		   e1.printStackTrace();
	   }
	 * 
	 * 
	 */
	
	static Window window;
	public static JOptionPane error = new JOptionPane();
	static Runtime runtime = Runtime.getRuntime();
	static String logName = "";
	public static void main(String[] args)
	{
		File asset1 = new File("assets/");
		File asset2 = new File("assets/button.png");
		File asset3 = new File("assets/clu.jpg");
		File html = new File("html/");
		File logs = new File("logs/");
		File crashs = new File("crash-reports/");
		if(!asset1.exists() || !asset2.exists() || !asset3.exists() || !html.exists() || !logs.exists() || !crashs.exists())
		{
			Initializing init = new Initializing();
			init.setValue(1);
			if(!asset1.exists())
			{
				asset1.mkdir();
			}
			init.setValue(13);
			if(!asset2.exists() || !asset3.exists())
			{
				try{
					Thread.sleep(500);
				} catch(Exception e){CrashReport.generateFile();
				try
				{
					e.printStackTrace(new PrintStream(CrashReport.fileName));
				} catch (FileNotFoundException e1)
				{
					e1.printStackTrace();
				}}
				init.setValue(38);
				Download.downloadFile("http://clu-project.esy.es/assets/button.png");
				init.setValue(43);
				try{
					Thread.sleep(500);
				} catch(Exception e){CrashReport.generateFile();
				try
				{
					e.printStackTrace(new PrintStream(CrashReport.fileName));
				} catch (FileNotFoundException e1)
				{
					e1.printStackTrace();
				}}
				init.setValue(67);
				Download.downloadFile("http://clu-project.esy.es/assets/clu.jpg");
			}
			init.setValue(73);
			if(!html.exists())
			{
				html.mkdir();
			}
			init.setValue(86);
			if(!logs.exists())
			{
				logs.mkdir();
			}
			init.setValue(93);
			if(!crashs.exists())
			{
				crashs.mkdir();
			}
			init.setValue(100);
			try{
				Thread.sleep(350);
			} catch(Exception e)
			{
				CrashReport.generateFile();
			try
			{
				e.printStackTrace(new PrintStream(CrashReport.fileName));
			} catch (FileNotFoundException e1)
			{
				e1.printStackTrace();
			}}
			init.dispose();
		}
		Calendar c = Calendar.getInstance();
		logName = "logs/LOG-" + c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.DAY_OF_MONTH) + "_" + c.get(Calendar.HOUR_OF_DAY) + "." + c.get(Calendar.MINUTE) + "." + c.get(Calendar.SECOND) + ".txt";
		File log = new File(logName);
		try
		{
			log.createNewFile();
		} catch (IOException e)
		{
			CrashReport.generateFile();
			try
			{
				e.printStackTrace(new PrintStream(CrashReport.fileName));
			} catch (FileNotFoundException e1)
			{
				e1.printStackTrace();
			}
		}
		//runtime.addShutdownHook(new ThreadShutdown());
		window = new Window("C.L.U.", 900, 706, null, JFrame.EXIT_ON_CLOSE, true, Color.WHITE, false);
	}
	
	public static void out(String text)
	{
		Window.addTextToOutput(text);
	}
	
	public static void getSystemInfo()
	{
		out("Récupération des infos système...");
		String osName;
		String osVersion;
		String osArch;
		String javaVersion;
		String javaVersionType;
		osName = System.getProperty("os.name");
		osVersion = System.getProperty("os.version");
		osArch = System.getProperty("os.arch");
		javaVersion = System.getProperty("java.version");
		javaVersionType = System.getProperty("sun.arch.data.model");
		out("Votre OS est : " + osName + " " + osVersion + " " + osArch);
		out("Votre version de Java™ SE est " + javaVersion + " " + javaVersionType + " bits");
	}
	
	public static void analyse(String input)
	{
		File out = new File(logName);
		input = input.toUpperCase();
		if(Save.isSaving)
		{
			Window.addTextToOutput(Window.getTextboxText());
			if(Save.dirIn == "" && Save.dirOut == "")
			{
				Save.dirIn = input;
				out("Veuillez spécifier un chemin de sortie :");
			}
			else if(Save.dirIn != "" && Save.dirOut == "")
			{
				Save.dirOut = input;
				out("Lancement de la sauvegarde.");
				Save.doSave();
			}
			else if(Save.dirIn != "" && Save.dirOut != "")
			{
				Save.isSaving = false;
			}
			else
			{
				Save.isSaving = false;
			}
		}
		else
		{
			int par1 = Recognizer.recognize(input);
			switch(par1)
			{
				case 0:
					error.showMessageDialog(null, "Erreur durant l'analyse", "Erreur", JOptionPane.ERROR_MESSAGE);
				case 1:
					out("Je suis C.L.U., Programme Utilitaire de Gestion (C.L.U. Logiciel Utilitaire).\nDroits : GESTION");
					break;
				case 2:
					String name1 = System.getProperty("user.name") + ", invité.\nDroits : AUCUN";
					if(System.getProperty("user.name") == "Thierry")
					{
						name1 = "Romain, maitre du système.\nDroits : DIEU";
					}
					out("Tu est " + name1);
					out(System.getProperty("user.name"));
					break;
				case 3:
					out("RINZLER, Programme de Défense du Système.\nDroits : PROTECTION");
					break;
				case 4:
					out("Fermeture du Sytème dans : 3s");
					try
					{
						Thread.sleep(1000);
					} catch (InterruptedException e)
					{
						CrashReport.generateFile();
						try
						{
							e.printStackTrace(new PrintStream(CrashReport.fileName));
						} catch (FileNotFoundException e1)
						{
							e1.printStackTrace();
						}
					}
					out("Fermeture du Sytème dans : 2s");
					try
					{
						Thread.sleep(1000);
					} catch (InterruptedException e)
					{
						CrashReport.generateFile();
						try
						{
							e.printStackTrace(new PrintStream(CrashReport.fileName));
						} catch (FileNotFoundException e1)
						{
							e1.printStackTrace();
						}
					}
					out("Fermeture du Sytème dans : 1s");
					try
					{
						Thread.sleep(1000);
					} catch (InterruptedException e)
					{
						CrashReport.generateFile();
						try
						{
							e.printStackTrace(new PrintStream(CrashReport.fileName));
						} catch (FileNotFoundException e1)
						{
							e1.printStackTrace();
						}
					}
					out("Fermeture du Sytème. C.L.U. : D�sactiv�");
					try
					{
						Thread.sleep(10);
					} catch (InterruptedException e)
					{
						CrashReport.generateFile();
						try
						{
							e.printStackTrace(new PrintStream(CrashReport.fileName));
						} catch (FileNotFoundException e1)
						{
							e1.printStackTrace();
						}
					}
					window.dispose();
					break;
				case 5 :
					CLU.out("Veuillez spécifier un chemin d'entr�e :");
					Save.isSaving = true;
					break;
				case 6:
					getSystemInfo();
					break;
				case 7:
					out("Ouverture de l'éditeur HTML.");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						CrashReport.generateFile();
						try
						{
							e.printStackTrace(new PrintStream(CrashReport.fileName));
						} catch (FileNotFoundException e1)
						{
							e1.printStackTrace();
						}
					}
					HTMLEditor html = new HTMLEditor();
					break;
				case 8:
					Window.clearOutput();
					break;
				case 9:
					out("Bonjour, " + System.getProperty("user.name"));
					break;
				case 10:
					out(String.valueOf(new Date().getHours()) + ":" + String.valueOf(new Date().getMinutes()) + ":" + String.valueOf(new Date().getSeconds()) + "s");
					break;
				case 11:
					out(new Date().toString());
					break;
				case 12:
					out(runtime.freeMemory() + " Octets sont utilisés par la JVM.");
					break;
				case 13:
					out(runtime.maxMemory() + " Octets sont alloués par la JVM.");
					break;
				case 14:
					/*ProcessBuilder processbuilder = new ProcessBuilder("C:\\CLU\\CLU.jar");
					Process process = null;
					try
					{
						process = processbuilder.start();
					} catch (IOException e)
					{
						e.printStackTrace();
					}
					process.destroy();*/
					out("En construction");
					break;
				case 15:
					out("En construction");
					break;
				case 16:
					out(runtime.totalMemory() + " Octets sont alloués à la JVM");
					break;
				case 17:
					runtime.halt(0);
					break;
				case 18:
					out(runtime.freeMemory() + " Octets sont utilisés par la JVM.");
					out("Garbage Collector activated !");
					runtime.gc();
					out("Garbage Collector terminated !");
					try
					{
						Thread.sleep(1000);
					} catch (InterruptedException e)
					{
						CrashReport.generateFile();
						try
						{
							e.printStackTrace(new PrintStream(CrashReport.fileName));
						} catch (FileNotFoundException e1)
						{
							e1.printStackTrace();
						}
					}
					out(runtime.freeMemory() + " Octets sont utilisés par la JVM.");
					break;
				case 19:
					out(runtime.availableProcessors() + " coeurs sont alloués à la JVM");
					break;
				case 20:
					final String os = System.getProperty("os.name");
			        if (os.contains("Windows"))
			        {
						/*ThreadRun tr = new ThreadRun();
						tr.setDir("cmd.exe");
						tr.setExecutionEnvrironment("C:\\Windows\\System32\\");
						tr.start();*/
						RunExternal.launch("C://WINDOWS//system32//cmd.exe");
					}
					break;
				default:
					out("Je n'ai pas compris. Attention aux fautes d'orthographe.");
			}
			Window.addTextToOutput(Window.getTextboxText());
		}
		try
		{
			FileWriter fw = new FileWriter(logName, true);
			BufferedWriter output = new BufferedWriter(fw);
			output.write(Window.getOutputText());
			output.flush();
			output.close();
		}
		catch(IOException e)
		{
			CrashReport.generateFile();
			try
			{
				e.printStackTrace(new PrintStream(CrashReport.fileName));
			} catch (FileNotFoundException e1)
			{
				e1.printStackTrace();
			}
		}
		Window.resetTextInput();
	}
}

class ThreadShutdown extends Thread
{
	@Override
	public void run()
	{
		CLU.out("Arrêt système dans : 3s");
		try
		{
			sleep(1000);
		} catch (InterruptedException e)
		{
			CrashReport.generateFile();
			try
			{
				e.printStackTrace(new PrintStream(CrashReport.fileName));
			} catch (FileNotFoundException e1)
			{
				e1.printStackTrace();
			}
		}
		CLU.out("Arrêt système dans : 2s");
		try
		{
			sleep(1000);
		} catch (InterruptedException e)
		{
			CrashReport.generateFile();
			try
			{
				e.printStackTrace(new PrintStream(CrashReport.fileName));
			} catch (FileNotFoundException e1)
			{
				e1.printStackTrace();
			}
		}
		CLU.out("Arrêt système dans : 1s");
		try
		{
			sleep(1000);
		} catch (InterruptedException e)
		{
			CrashReport.generateFile();
			try
			{
				e.printStackTrace(new PrintStream(CrashReport.fileName));
			} catch (FileNotFoundException e1)
			{
				e1.printStackTrace();
			}
		}
		CLU.out("Arrêt système.");
	}
}

class ThreadRun extends Thread
{
	private String dir = "";
	private String exenv = "";
	@Override
	public void run()
	{
		try
		{
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec(new String[]{"C:/Windows/System32/cmd.exe"}/*, null, new File(exenv)*/);
			CLU.out(String.valueOf(process.isAlive()));
			CLU.out(process.toString());
		}
		catch(Exception e)
		{
			CrashReport.generateFile();
			try
			{
				e.printStackTrace(new PrintStream(CrashReport.fileName));
			} catch (FileNotFoundException e1)
			{
				e1.printStackTrace();
			}
		}
	}
  
	public void setDir(String dir)
	{
		this.dir = dir;
	}
	
	public void setExecutionEnvrironment(String env)
	{
		this.exenv = env;
	}
}

class RunExternal 
{
    public static void launch(String command) 
    {
        try 
        {
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(command);
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            //Attendre la fin de l'execution
            if (process.waitFor() != 0) {
                System.out.println("Une erreur est survenue ");
            }
        } catch (InterruptedException ex) {
        	CrashReport.generateFile();
			try
			{
				ex.printStackTrace(new PrintStream(CrashReport.fileName));
			} catch (FileNotFoundException e1)
			{
				e1.printStackTrace();
			}
        } catch (IOException ex) 
        {
        	CrashReport.generateFile();
			try
			{
				ex.printStackTrace(new PrintStream(CrashReport.fileName));
			} catch (FileNotFoundException e1)
			{
				e1.printStackTrace();
			}
        }
    }
}
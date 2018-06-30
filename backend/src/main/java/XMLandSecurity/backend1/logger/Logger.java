package XMLandSecurity.backend1.logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Logger {

    private static Logger instance = null;

    private Logger() {
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    private void initializeLogger() {
    }

    public void log(String poruka) throws IOException {

//        InetAddress iAddress = InetAddress.getLocalHost();
//        String currentIp = iAddress.getHostAddress();
        // System.out.println("Current IP address : " +currentIp);

        BufferedWriter bw = null;
        FileWriter fw = null;
        fw = new FileWriter("logs.txt", true);
        bw = new BufferedWriter(fw);
        bw.append(poruka);
        bw.newLine();
        bw.close();
    }

    public void logTrenutni(String poruka) throws IOException {

//        InetAddress iAddress = InetAddress.getLocalHost();
//        String currentIp = iAddress.getHostAddress();

        BufferedWriter bw = null;
        FileWriter fw = null;
        fw = new FileWriter("warning.txt", true);
        bw = new BufferedWriter(fw);
        bw.append(poruka);
        bw.newLine();
        bw.close();
    }

    public void logError(String poruka) throws IOException {
//        InetAddress iAddress = InetAddress.getLocalHost();
//        String currentIp = iAddress.getHostAddress();

        BufferedWriter bw = null;
        FileWriter fw = null;
        fw = new FileWriter("error.txt", true);
        bw = new BufferedWriter(fw);
        bw.append(poruka);
        bw.newLine();
        bw.close();
    }

}
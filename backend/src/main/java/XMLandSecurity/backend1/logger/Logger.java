package XMLandSecurity.backend1.logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    private static Logger instance = null;

    private Logger(){
        initializeLogger();
    }

    private void initializeLogger() {
    }

    public void log(String poruka) throws IOException {
        BufferedWriter bw = null;
        FileWriter fw = null;
        fw = new FileWriter("logs.txt",true);
        bw = new BufferedWriter(fw);
        bw.append(poruka);
        bw.newLine();
        bw.close();
    }

    public static Logger getInstance() {
        if(instance == null){
            instance = new Logger();
        }
        return instance;
    }

}
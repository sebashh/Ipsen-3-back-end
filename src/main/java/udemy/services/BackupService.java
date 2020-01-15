package udemy.services;


import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BackupService {

    private String host = "localhost";
    private String database = "Ipsen3";
    //private String database = "PLNT";
    //private String path ="C:\\Users\\Jaime\\Desktop\\backups\\";
    private String path = System.getProperty("user.home") + "\\Desktop\\";
    private LocalDateTime currentDateTime;
    private String user;
    private String password;

    public BackupService(){
        setupBackupConfiguration();
        createBackUpService();
    }

    private void createBackUpService() {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            try {
                createBackup();
            } catch (IOException e) {
                e.printStackTrace();
            }
        },0,30, TimeUnit.DAYS);
    }

    private void setupBackupConfiguration(){
        File file = new File("config.yml");
        ArrayList<String> strings = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (br.ready()) {
                strings.add(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        user = strings.get(10).split(":")[1].trim();
        password = strings.get(13).split(":")[1].trim();
    }


    private void createBackup() throws IOException {
        currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String outputFile = path + "PLNT" + dateTimeFormatter.format(currentDateTime) + ".sql";
        Runtime runtime = Runtime.getRuntime();
        Process process;
        ProcessBuilder processBuilder;

        System.out.println("backup started");
        runtime = Runtime.getRuntime();
        processBuilder = new ProcessBuilder(
                "C:\\Program Files\\PostgreSQL\\10\\bin\\pg_dump.exe",
                "-v","-h",host,"-f", outputFile, "-U", user, database);
        processBuilder.environment().put("PGPASSWORD", password);
        processBuilder.redirectErrorStream(true);
        process = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = reader.readLine();
        while (line !=null){
            line = reader.readLine();
        }
        System.out.println("backup completed");
    }
}
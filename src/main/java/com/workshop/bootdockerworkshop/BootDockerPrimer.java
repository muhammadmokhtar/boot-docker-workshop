package com.workshop.bootdockerworkshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class BootDockerPrimer implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

        String dockerRunCommand = "docker run -d -p 5432:5432 ghusta/postgres-world-db";
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", dockerRunCommand);
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = inputReader.readLine()) != null) {
            System.out.println("input---");
            System.out.println(line);
        }
        inputReader.close();
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        while ((line = errorReader.readLine()) != null) {
            System.out.println("error---");
            System.out.println(line);
        }
        errorReader.close();
        int exitVal = p.waitFor();
        if (exitVal == 0) {
            System.out.println("process executed successfully");
        } else {
            System.out.println("unsuccessful process execution");
        }
        p.destroy();
    }
}

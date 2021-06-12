package com.woven;

import com.woven.cli.FileSystemCli;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import picocli.CommandLine;

@SpringBootApplication
public class Main {

  public static String[] sargs;

  public static void main(String[] args) {
    sargs = args;
    var app = SpringApplication.run(Main.class, args);
  }

  @EventListener(ApplicationReadyEvent.class)
  void run(ApplicationReadyEvent event) {
    int exitCode = new CommandLine(new FileSystemCli()).execute(sargs);
    System.exit(exitCode);
  }
}

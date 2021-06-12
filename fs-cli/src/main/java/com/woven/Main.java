package com.woven;

import com.woven.command.FileSystemDeleteCmd;
import com.woven.command.FileSystemListCmd;
import com.woven.command.FileSystemUploadCmd;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import picocli.CommandLine;

@CommandLine.Command(name = "fs-cli",
        mixinStandardHelpOptions = true,
        version = "1.0",
        description = "FileSystem Server CLI"
)
@SpringBootApplication
public class Main implements CommandLineRunner {

  public static void main(String[] args) {
    var app = SpringApplication.run(Main.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    CommandLine commandLine = new CommandLine(new Main());
    commandLine.addSubcommand("upload", new FileSystemUploadCmd());
    commandLine.addSubcommand("delete", new FileSystemDeleteCmd());
    commandLine.addSubcommand("list", new FileSystemListCmd());
    commandLine.parseWithHandler(new CommandLine.RunLast(), args);
    System.exit(0);
  }
}

package com.woven;

import com.woven.command.FileSystemDeleteCmd;
import com.woven.command.FileSystemListCmd;
import com.woven.command.FileSystemUploadCmd;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import picocli.CommandLine;

@CommandLine.Command(name = "fs-cli",
        mixinStandardHelpOptions = true,
        version = "1.0",
        description = "FileSystem Server CLI"
)
@SpringBootApplication
public class Main implements CommandLineRunner {
  private final FileSystemUploadCmd fileSystemUploadCmd;
  private final FileSystemDeleteCmd fileSystemDeleteCmd;
  private final FileSystemListCmd fileSystemListCmd;

  public Main(FileSystemUploadCmd fileSystemUploadCmd, FileSystemDeleteCmd fileSystemDeleteCmd, FileSystemListCmd fileSystemListCmd) {
    this.fileSystemUploadCmd = fileSystemUploadCmd;
    this.fileSystemDeleteCmd = fileSystemDeleteCmd;
    this.fileSystemListCmd = fileSystemListCmd;
  }

  public static void main(String[] args) {
    SpringApplication springApplication = new SpringApplication(Main.class);
    springApplication.setWebApplicationType(WebApplicationType.NONE);
    springApplication.setBannerMode(Banner.Mode.OFF);
    springApplication.run(args);
  }

  @Override
  public void run(String... args) throws Exception {
    CommandLine commandLine = new CommandLine(new Main(fileSystemUploadCmd, fileSystemDeleteCmd, fileSystemListCmd));
    commandLine.addSubcommand("upload", fileSystemUploadCmd);
    commandLine.addSubcommand("delete", fileSystemDeleteCmd);
    commandLine.addSubcommand("list", fileSystemDeleteCmd);
    commandLine.parseWithHandler(new CommandLine.RunLast(), args);
    System.exit(0);
  }
}

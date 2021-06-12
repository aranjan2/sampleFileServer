package com.woven.command;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import picocli.CommandLine;

import java.nio.file.Path;
import java.util.List;

@Component
@CommandLine.Command(
        name = "delete",
        description = "Delete file from remote server")
public class FileSystemDeleteCmd implements Runnable {
  org.slf4j.Logger logger = LoggerFactory.getLogger(FileSystemDeleteCmd.class);

  private final WebClient webClient;

  @CommandLine.Parameters(index = "0..*", description = "List of the files")
  private List<Path> files;

  public FileSystemDeleteCmd(WebClient webClient) {
    this.webClient = webClient;
  }


  @Override
  public void run() {
    logger.debug("Deleting files {}", files);
  }
}
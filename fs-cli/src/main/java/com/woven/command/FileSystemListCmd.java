package com.woven.command;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import picocli.CommandLine;

@Component
@CommandLine.Command(
        name = "list",
        description = "Uploads file to remote server")
public class FileSystemListCmd implements Runnable {
  org.slf4j.Logger logger = LoggerFactory.getLogger(FileSystemListCmd.class);

  private final WebClient webClient;

  @CommandLine.Parameters(index = "0", description = "path prefix")
  private String prefix;

  public FileSystemListCmd(WebClient webClient) {
    this.webClient = webClient;
  }

  @Override
  public void run() {
    logger.debug("listing prefix {}", prefix);

  }


}
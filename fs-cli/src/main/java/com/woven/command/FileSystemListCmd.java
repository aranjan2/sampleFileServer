package com.woven.command;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import picocli.CommandLine;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@CommandLine.Command(
        name = "list",
        description = "Uploads file to remote server")
public class FileSystemListCmd implements Runnable {
  org.slf4j.Logger logger = LoggerFactory.getLogger(FileSystemListCmd.class);

  private final WebClient webClient;

  @CommandLine.Option(required = false, names = "prefix", description = "path prefix")
  private String prefix;

  public FileSystemListCmd(WebClient webClient) {
    this.webClient = webClient;
  }

  @Override
  public void run() {
    logger.debug("listing prefix {}", prefix);
    System.out.println("Listing contents:");
    try {
      AtomicInteger count = new AtomicInteger(0);
      webClient.get()
              .uri("http://localhost:8080/v1/fileserver/files")
              .retrieve()
              .bodyToMono(List.class)
              .block()
              .forEach(file -> {
                System.out.println("[" + count.incrementAndGet() + "] " + file);
              });
    } catch (Exception e) {
      logger.error("Error listing files", e);
      System.out.println("Failed with Error : " + e.getMessage());
    }
  }


}
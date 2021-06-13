package com.woven.command;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import picocli.CommandLine;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@CommandLine.Command(
        name = "delete",
        description = "Delete file from remote server")
public class FileSystemDeleteCmd implements Runnable {
  org.slf4j.Logger logger = LoggerFactory.getLogger(FileSystemDeleteCmd.class);

  private final WebClient webClient;

  @CommandLine.Parameters(index = "0..*", description = "List of the files")
  private List<String> files;

  public FileSystemDeleteCmd(WebClient webClient) {
    this.webClient = webClient;
  }

  @Value("${server-url}")
  private String serverUrl;

  @Override
  public void run() {
    logger.debug("Deleting files {}", files);
    System.out.println("Deleting files.. ");
    try {
      AtomicInteger count = new AtomicInteger(1);
      files.forEach(file -> {
        System.out.print("[" + count.getAndIncrement() + "]  File=" + file);
        try {
          webClient.delete()
                  .uri(serverUrl+"/v1/fileserver/files/" + file)
                  .retrieve()
                  .bodyToMono(String.class)
                  .block();
          System.out.println(" Done..✅");

        } catch (WebClientException e) {
          if (e.getMessage() != null && e.getMessage().contains("404")) {
            logger.error("error deleting", e);
            System.out.println(" Doesn't Exist..❌");
          }else {
            throw e;
          }
        }
      });
    } catch (Exception e) {
      System.out.println(" Delete failed...failed ERROR:" + e.getMessage());
      logger.error("Error Occured while deleting", e);
      System.exit(-1);
    }
    System.exit(0);
  }
}
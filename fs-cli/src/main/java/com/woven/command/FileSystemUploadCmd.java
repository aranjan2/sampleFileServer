package com.woven.command;

import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import picocli.CommandLine;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@CommandLine.Command(
        name = "upload",
        description = "Uploads file to remote server")
public class FileSystemUploadCmd implements Runnable {
  org.slf4j.Logger logger = LoggerFactory.getLogger(FileSystemUploadCmd.class);


  @CommandLine.Parameters(index = "0..*", description = "List of the files")
  private List<Path> files;
  private final WebClient webClient;

  public FileSystemUploadCmd(WebClient webClient) {
    this.webClient = webClient;
  }

  @Override
  public void run() { // your business logic goes here...
    logger.debug("uploading file {}", files);
    System.out.println("Uploading files.. ");
    try {
      AtomicInteger count = new AtomicInteger(1);
      files.forEach(file -> {
        System.out.print("[" + count.getAndIncrement() + "]  File=" + file);
        webClient.post()
                .uri("http://localhost:8080/v1/fileserver/files")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(fromFile(file.toFile())))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println(" ...done");
      });
    } catch (Exception e) {
      System.out.println(" Upload failed...failed");
      System.out.println("Error Occurred :" + e.getMessage());
      System.exit(-1);
    }
    System.exit(0);
  }

  public MultiValueMap<String, HttpEntity<?>> fromFile(File file) {
    MultipartBodyBuilder builder = new MultipartBodyBuilder();
    builder.part("file", new FileSystemResource(file));
    return builder.build();
  }

}
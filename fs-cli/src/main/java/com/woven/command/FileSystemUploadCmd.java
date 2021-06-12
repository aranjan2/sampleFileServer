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

@Component
@CommandLine.Command(
        name = "upload",
        description = "Uploads file to remote server")
public class FileSystemUploadCmd implements Runnable {
  org.slf4j.Logger logger = LoggerFactory.getLogger(FileSystemUploadCmd.class);


  @CommandLine.Parameters(index = "0..*", description = "List of the files")
  private List<Path> files;

  @Override
  public void run() { // your business logic goes here...
    logger.debug("uploading file {}", files);
    var webClient = WebClient.builder().baseUrl(System.getenv("FS_SERVER_BASE")).build();

    try {
      webClient.post()
              .uri("http://localhost:8080/uploadFile")
              .contentType(MediaType.MULTIPART_FORM_DATA)
              .body(BodyInserters.fromMultipartData(fromFile(new File("HELP.md"))))
              .retrieve()
              .bodyToMono(String.class)
              .block();
    } catch (Exception e) {
      System.out.println("Error Occured :" + e.getMessage());
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
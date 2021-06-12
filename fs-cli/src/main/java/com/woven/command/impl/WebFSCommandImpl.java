package com.woven.command.impl;

import com.woven.command.FSCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;


@Component
public class WebFSCommandImpl implements FSCommand {
  private WebClient webClient;
  Logger logger = LoggerFactory.getLogger(WebFSCommandImpl.class);

  public WebFSCommandImpl() {
    this.webClient = WebClient.builder().baseUrl(System.getenv("FS_SERVER_BASE")).build();
  }

  final static RestTemplate restTemplate = new RestTemplate();

  @Override
  public void delete(String fileName) {
    logger.debug("Deleting file {}", fileName);

  }

  @Override
  public void upload(String fileName) {
    logger.debug("uploading file {}", fileName);

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
    }
  }

  public MultiValueMap<String, HttpEntity<?>> fromFile(File file) {
    MultipartBodyBuilder builder = new MultipartBodyBuilder();
    builder.part("file", new FileSystemResource(file));
    return builder.build();
  }

  @Override
  public void list(String pathPrefix) {
    logger.debug("listing contents:");
  }
}

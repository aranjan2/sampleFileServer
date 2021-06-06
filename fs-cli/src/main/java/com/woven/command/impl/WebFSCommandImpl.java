package com.woven.command.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.woven.command.FSCommand;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;


@Component
public class WebFSCommandImpl implements FSCommand {
  private WebClient webClient;

  public WebFSCommandImpl() {
    this.webClient = WebClient.builder().baseUrl(System.getenv("FS_SERVER_BASE")).build(); ;
  }

  Logger logger = Logger.getLogger(String.valueOf(WebFSCommandImpl.class));
  final static RestTemplate restTemplate = new RestTemplate();

  @Override
  public void delete(String fileName) {
    logger.log(Level.FINE,"Deleting file" + fileName);

  }

  @Override
  public void upload(String fileName) {
    logger.log(Level.FINE,"uploading file" + fileName);

    webClient.post()
            .uri("http://localhost:8080/uploadFile")
            .contentType(MediaType.MULTIPART_FORM_DATA)
            .body(BodyInserters.fromMultipartData(fromFile(new File("HELP.md"))))
            .retrieve()
            .bodyToMono(String.class)
            .block();
  }

  public MultiValueMap<String, HttpEntity<?>> fromFile(File file) {
    MultipartBodyBuilder builder = new MultipartBodyBuilder();
    builder.part("file", new FileSystemResource(file));
    return builder.build();
  }

  @Override
  public void list(String pathPrefix) {
    logger.log(Level.FINE,"listing contents:");
  }
}

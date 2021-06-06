package com.woven.command.impl;

import org.springframework.web.client.RestTemplate;
import com.woven.command.FSCommand;

import java.util.logging.Level;
import java.util.logging.Logger;


public class WebFSCommandImpl implements FSCommand {
  Logger logger = Logger.getLogger(String.valueOf(WebFSCommandImpl.class));
  final static RestTemplate restTemplate = new RestTemplate();

  @Override
  public void delete(String fileName) {
    logger.log(Level.FINE,"Deleting file" + fileName);

  }

  @Override
  public void upload(String fileName) {
    logger.log(Level.FINE,"uploading file" + fileName);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.MULTIPART_FORM_DATA);

    MultiValueMap<String, Object> body
            = new LinkedMultiValueMap<>();
    body.add("file", fileName);
    HttpEntity<MultiValueMap<String, Object>> requestEntity
            = new HttpEntity<>(body, headers);



    String serverUrl = "http://localhost:8080/uploadFile";

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = restTemplate
            .postForEntity(serverUrl, requestEntity, String.class);


  }

  @Override
  public void list(String pathPrefix) {
    logger.log(Level.FINE,"listing contents:");
  }
}

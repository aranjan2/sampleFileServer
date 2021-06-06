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


  }

  @Override
  public void list(String pathPrefix) {
    logger.log(Level.FINE,"listing contents:");
  }
}

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
import java.util.concurrent.Callable;

@Component
@CommandLine.Command(
        name = "list",
        description = "Uploads file to remote server")
public class FileSystemListCmd implements Runnable {
  org.slf4j.Logger logger = LoggerFactory.getLogger(FileSystemListCmd.class);


  @CommandLine.Parameters(index = "0", description = "path prefix")
  private String prefix;

  @Override
  public void run() {
    logger.debug("listing prefix {}", prefix);

  }


}
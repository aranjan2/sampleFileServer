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
        name = "delete",
        description = "Delete file from remote server")
public class FileSystemDeleteCmd implements Runnable {
  org.slf4j.Logger logger = LoggerFactory.getLogger(FileSystemDeleteCmd.class);


  @CommandLine.Parameters(index = "0..*", description = "List of the files")
  private List<Path> files;


  @Override
  public void run() {
    logger.debug("Deleting files {}", files);
  }
}
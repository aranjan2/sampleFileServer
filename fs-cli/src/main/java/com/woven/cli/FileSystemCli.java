package com.woven.cli;

import com.woven.command.FSCommand;
import com.woven.command.impl.WebFSCommandImpl;
import picocli.CommandLine;

import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

@CommandLine.Command(name = "FileSystem", mixinStandardHelpOptions = true,
        version = "delete 1.0",
        description = "Delete the files from FileSystem Server")
public class FileSystemCli implements Callable<Integer> {
  Logger logger = Logger.getLogger("FsCli");
  private final FSCommand fsCommand = new WebFSCommandImpl();

  @CommandLine.Parameters(index = "0", description = "Parameters for the operation")
  private String param;

  @CommandLine.Option(names = {"-o", "--operation"}, description = "LIST, UPLOAD, DELETE")
  private String operation = "NONE";

  @Override
  public Integer call() throws Exception { // your business logic goes here...
    switch (operation.toUpperCase()) {
      case "DELETE":
        fsCommand.delete(param);
        break;
      case "LIST":
        fsCommand.list(param);
        break;
      case "UPLOAD":
        fsCommand.upload(param);
        break;
      default:
        logger.log(Level.SEVERE, "Unsupported option");
    }
    return 0;
  }
}
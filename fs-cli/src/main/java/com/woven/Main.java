package com.woven;

import com.woven.cli.FileSystemCli;
import picocli.CommandLine;

public class Main {
  public static void main(String[] args) {
    int exitCode = new CommandLine(new FileSystemCli()).execute(args);
    System.exit(exitCode);
  }
}

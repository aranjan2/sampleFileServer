package com.woven.command;

public interface FSCommand {
  /**
   * Deletes files from Supported System given fileName
   * @param fileName fileName
   */
  void delete(String fileName);

  /**
   * Upload file to supported file system
   * @param FileName fileName
   */
  void upload(String FileName);

  /**
   * List all the files in given path prefix
   * @param pathPrefix Path prefix in the file system
   */
  void list(String pathPrefix);
}

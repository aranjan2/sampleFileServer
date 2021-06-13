package com.woven.assignment.controller;


import com.woven.assignment.exception.AssignmentException;
import com.woven.assignment.exception.ErrorResponse;
import com.woven.assignment.exception.FileSizeException;
import com.woven.assignment.storage.StorageService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.File;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static com.woven.assignment.exception.ErrorCodes.WOVEN_FILE_SERVICE_SIZE_EXCEEDED_CODE;
import static com.woven.assignment.exception.ErrorMessages.WOVEN_FILE_SERVICE_SIZE_EXCEEDED;


@Controller
@RequestMapping("/v1/fileserver/files")
public class FileController {

    Logger logger = LoggerFactory.getLogger(FileController.class);
    private final StorageService storageService;
    private final String dnsName = "localhost:8080/files/";
    private final long MAX_FILE_SIZE = 51_200_000;

    public FileController(StorageService storageService) {
        this.storageService = storageService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> submit(@RequestParam("file") final MultipartFile file, final ModelMap modelMap) {

        modelMap.addAttribute("file", file);
        logger.info("file upload name is {}", file.getName());
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new FileSizeException(new ErrorResponse(WOVEN_FILE_SERVICE_SIZE_EXCEEDED_CODE,
                    WOVEN_FILE_SERVICE_SIZE_EXCEEDED));
        }

        storageService.store(file);
        return ResponseEntity.created(URI.create(dnsName + file.getName())).build();
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> list() {
        return ResponseEntity.ok(storageService.listAll());
    }


    @DeleteMapping(value = "/{fileName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable String fileName) {
        storageService.delete(fileName);
        return ResponseEntity.ok().build();
    }


}
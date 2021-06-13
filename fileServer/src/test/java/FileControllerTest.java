import com.woven.assignment.controller.FileController;
import com.woven.assignment.storage.FileSystemStorageService;
import com.woven.assignment.storage.StorageProperties;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {FileController.class, FileSystemStorageService.class, StorageProperties.class})
@EnableAutoConfiguration
public class FileControllerTest {


  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;


  public void setup() {
    try {
      File file = new File("upload-dir");
      if (!file.exists()) {
        file.mkdir();
      } else {
        System.out.println("Directory exists..probably some test stoped mid way");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void tearUp() {
    try {
      System.out.println("Deleting bullshit files...");
      try {
        java.lang.Runtime.getRuntime().exec("rm -Rf upload-dir");
      } catch (IOException e) {
        e.printStackTrace();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  @Test
  public void fileListWithUpload() throws Exception {

    tearUp();
    setup();
    String url = "http://localhost:" + port + "/v1/fileserver/files";

    //Uploads a temp file
    String fileName = uploadAndAssert(url);

    // List files and assert
    assertThat(this.restTemplate.getForObject(url,
            String.class))
            .contains("test-file");

    // List files and assert
    Assert.assertEquals(this.restTemplate.getForObject(url,
            List.class).size(), 1);

    restTemplate.delete(url + "/" + fileName, String.class);

    // List files and assert
    Assert.assertEquals(this.restTemplate.getForObject(url,
            List.class).size(), 0);

    //Tear Up
  }

  private String uploadAndAssert(String url) throws IOException {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.MULTIPART_FORM_DATA);

    MultiValueMap<String, Object> body
            = new LinkedMultiValueMap<>();
    Resource testFile = getTestFile();
    body.add("file", testFile);

    HttpEntity<MultiValueMap<String, Object>> requestEntity
            = new HttpEntity<>(body, headers);


    ResponseEntity<String> response = restTemplate
            .postForEntity(url, requestEntity, String.class);

    Assert.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    return testFile.getFilename();
  }

  private Resource getTestFile() throws IOException {
    Path testFile = Files.createTempFile("test-file", ".txt");
    System.out.println("Creating and Uploading Test File: " + testFile);
    Files.write(testFile, "Hello World !!, This is a test file.".getBytes());
    return new FileSystemResource(testFile.toFile());
  }

}

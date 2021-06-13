package com.woven.command;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import org.testng.Assert;

@SpringBootTest
class FileSystemDeleteCmdTest {

  private WebClient webClient = Mockito.mock(WebClient.class);

  private FileSystemDeleteCmd fileSystemDeleteCmd = new FileSystemDeleteCmd(webClient);

  @Test
  public void run() {
    WebClient.RequestHeadersUriSpec resp = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
    Mockito.when(webClient.delete()).thenReturn(resp);
    WebClient.RequestHeadersSpec headSpec = Mockito.mock(WebClient.RequestBodySpec.class);
    Mockito.when(resp.uri(Mockito.anyString())).thenReturn(headSpec);
    WebClient.ResponseSpec respSpec = Mockito.mock(WebClient.ResponseSpec.class);
    Mockito.when(headSpec.retrieve()).thenReturn(respSpec);

    try {
      fileSystemDeleteCmd.run();
    } catch (Exception e) {
      Assert.fail("Exception should not occure");
    }
  }

}
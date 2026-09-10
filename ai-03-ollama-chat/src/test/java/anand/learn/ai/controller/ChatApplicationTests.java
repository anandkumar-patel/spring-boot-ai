package anand.learn.ai.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class ChatApplicationTests {

    @Autowired
    MockMvcTester mockMvcTester;

    @Test
    void chat() {
        MvcTestResult testResult = mockMvcTester.post().uri("/api/chat")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "prompt": "Tell me a Java joke"
                        }
                        """)
                .exchange();
        assertThat(testResult)
                .hasStatus(HttpStatus.OK)
                .bodyJson()
                .convertTo(ChatController.Output.class).satisfies(output -> {
                    assertThat(output.content()).isNotBlank();
                    System.out.println("Response:\n"+output.content());
                });

    }

}



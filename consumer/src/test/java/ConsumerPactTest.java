import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ConsumerPactTest {
    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("test-provider", "localhost", 8080, this);

    @Pact(consumer = "test-consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return builder
                .given("test GET")
                .uponReceiving("GET REQUEST")
                .path("/")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body("{\"name\": \"Hari Krishnan\"}")
                .toPact();
    }

    @Test
    @PactVerification
    public void shouldRespondWith200() {
        ResponseEntity<String> response = new RestTemplate().getForEntity(mockProvider.getUrl(), String.class);
        assertThat(response.getStatusCode().value(), is(equalTo(200)));
//        assertThat(response.getHeaders().getContentType(), is(equalTo("application/json")));
        assertThat(response.getBody(), is(equalTo("{\"name\":\"Hari Krishnan\"}")));
    }
}

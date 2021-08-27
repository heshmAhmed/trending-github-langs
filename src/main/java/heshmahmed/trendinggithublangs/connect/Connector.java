package heshmahmed.trendinggithublangs.connect;

import heshmahmed.trendinggithublangs.model.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
@Slf4j
public class Connector {
    private final WebClient.Builder webClient;

    @Value("${github.base.url}")
    private String BASE_URL;

    public Flux<Response> callGithubApi(String date){
       return this.webClient
               .codecs(configurer->configurer.defaultCodecs().maxInMemorySize(1024 * 1024))
                .build()
                .get()
                .uri(BASE_URL, date)
               .retrieve()
               .bodyToFlux(Response.class);
    }


}

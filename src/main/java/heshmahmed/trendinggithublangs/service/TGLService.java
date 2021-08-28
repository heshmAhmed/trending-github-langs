package heshmahmed.trendinggithublangs.service;

import heshmahmed.trendinggithublangs.connect.Connector;
import heshmahmed.trendinggithublangs.model.Response;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class TGLService implements ITGLService{
    private final Connector connector;
    private final String dateFormat = new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime() - (30L * 24 * 60 * 60 * 1000));

    @Override
    public Flux<Response> getTrendingRepos() throws RuntimeException {
        return connector.callGithubApi(dateFormat).flatMap(
                response -> {
                    response.count=response.items.size(); return Flux.just(response);
                }).doOnError(throwable -> {
                    log.info("Error: ", throwable.getMessage());
                    throw new RuntimeException("Bad request");
        });
    }

    @Override
    public Map<String, Response> getTrendingLanguages() throws RuntimeException{
        return prepTLResponse(Objects.requireNonNull(this.getTrendingRepos().blockFirst()));
    }

    /**
     * Prepare trending languages response
     * @param trendingRepos Response object
     * @return Map<String, Response>
     */
    private Map<String, Response> prepTLResponse(@NonNull Response trendingRepos){
        Map<String, Response> map = new HashMap<>();
        trendingRepos.getItems().stream().filter(repository -> repository.getLanguage() != null)
                .forEach(repository -> Optional.ofNullable(map.get(repository.getLanguage()))
                        .ifPresentOrElse(response -> {
                            response.items.add(repository); response.count++;
                            }, ()->
                                map.put(repository.getLanguage(),
                                        Response.builder().items(new ArrayList<>(List.of(repository))).count(1).build())));
        return map;
    }
}

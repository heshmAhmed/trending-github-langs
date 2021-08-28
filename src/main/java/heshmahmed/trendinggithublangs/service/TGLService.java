package heshmahmed.trendinggithublangs.service;

import heshmahmed.trendinggithublangs.connect.Connector;
import heshmahmed.trendinggithublangs.model.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TGLService implements ITGLService{
    private final Connector connector;
    private final String dateFormat = new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime() - (60L * 24 * 60 * 60 * 1000));

    @Override
    public Flux<Response> getTrendingRepos() {
        return connector.callGithubApi(dateFormat).flatMap(response -> {response.count=response.items.size(); return Flux.just(response);});
    }

    @Override
    public Map<String, Response> getTrendingLanguages() {
        Map<String, Response> map = new HashMap<>();
        Objects.requireNonNull(this.getTrendingRepos().blockFirst())
                .getItems().stream().filter(repository -> repository.getLanguage() != null)
                .forEach(repository -> Optional.ofNullable(map.get(repository.getLanguage()))
                        .ifPresentOrElse(response -> {
                            response.items.add(repository); response.count++;},
                                ()->
                            map.put(repository.getLanguage(), Response.builder().items(new ArrayList<>(List.of(repository))).count(1).build())));
        return map;
    }
}

package heshmahmed.trendinggithublangs.service;

import heshmahmed.trendinggithublangs.model.Response;
import reactor.core.publisher.Flux;
import java.util.Map;


public interface ITrendingReposService {
    Flux<Response> getTrendingRepos();
    Map<String, Response> getLanguages();
}

package heshmahmed.trendinggithublangs.service;

import heshmahmed.trendinggithublangs.model.Response;
import reactor.core.publisher.Flux;

import java.util.Map;


public interface ITGLService {
    Flux<Response> getTrendingRepos();
    Map<String, Response> getTrendingLanguages();
}

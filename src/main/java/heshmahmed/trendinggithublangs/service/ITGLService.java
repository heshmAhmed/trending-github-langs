package heshmahmed.trendinggithublangs.service;

import heshmahmed.trendinggithublangs.model.Response;
import reactor.core.publisher.Flux;


public interface ITGLService {
    Flux<Response> getTrendingRepos();
}

package heshmahmed.trendinggithublangs.resource;

import heshmahmed.trendinggithublangs.model.Response;
import heshmahmed.trendinggithublangs.service.ITGLService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class TGLResource {
    private final ITGLService itglService;

    @RequestMapping("/trending-repos")
    public Flux<Response> GetTendingRepos(){
        return itglService.getTrendingRepos();
    }
}

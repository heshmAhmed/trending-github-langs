package heshmahmed.trendinggithublangs.resource;

import heshmahmed.trendinggithublangs.model.Response;
import heshmahmed.trendinggithublangs.service.ITGLService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class TGLResource {
    private final ITGLService itglService;

    @GetMapping("/trending-repos")
    public Flux<Response> GetTrendingRepos(){
        return itglService.getTrendingRepos();
    }

    @GetMapping("/trending-repos/langs")
    public Map GetTrendingLangs(){
        return itglService.getTrendingLanguages();
    }
}

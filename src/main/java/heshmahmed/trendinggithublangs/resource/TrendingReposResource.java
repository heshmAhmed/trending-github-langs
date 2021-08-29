package heshmahmed.trendinggithublangs.resource;

import heshmahmed.trendinggithublangs.model.Response;
import heshmahmed.trendinggithublangs.service.ITrendingReposService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class TrendingReposResource {
    private final ITrendingReposService service;

    @GetMapping("/trending-repos")
    public ResponseEntity<Flux<Response>> GetTrendingRepos(){
        try {
            return new ResponseEntity<>(service.getTrendingRepos(), HttpStatus.ACCEPTED);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/trending-repos/languages")
    public ResponseEntity<Map<String, Response>> GetLanguages(){
        try {
            return new ResponseEntity<>(service.getLanguages(), HttpStatus.ACCEPTED);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }    }
}

package heshmahmed.trendinggithublangs.resource;

import heshmahmed.trendinggithublangs.model.Response;
import heshmahmed.trendinggithublangs.service.ITGLService;
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
public class TGLResource {
    private final ITGLService service;

    @GetMapping("/trending-repos")
    public ResponseEntity<Flux<Response>> GetTrendingRepos(){
        try {
            return new ResponseEntity<>(service.getTrendingRepos(), HttpStatus.ACCEPTED);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/trending-repos/langs")
    public ResponseEntity<Map<String, Response>> GetTrendingLanguages(){
        try {
            return new ResponseEntity<>(service.getTrendingLanguages(), HttpStatus.ACCEPTED);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }    }
}

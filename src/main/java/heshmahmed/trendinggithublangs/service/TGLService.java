package heshmahmed.trendinggithublangs.service;

import heshmahmed.trendinggithublangs.connect.Connector;
import heshmahmed.trendinggithublangs.model.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TGLService implements ITGLService{
    private final Connector connector;
    private final String dateFormat = new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime() - (30L * 24 * 60 * 60 * 1000));

    @Override
    public Flux<Response> getTrendingRepos() {
        return connector.callGithubApi(dateFormat);
    }
}

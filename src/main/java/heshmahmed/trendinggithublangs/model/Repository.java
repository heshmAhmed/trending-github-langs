package heshmahmed.trendinggithublangs.model;

import lombok.Data;

@Data
public class Repository {
    private Integer id;
    private String name;
    private String html_url;
    private String languages_url;
    private String language;
}

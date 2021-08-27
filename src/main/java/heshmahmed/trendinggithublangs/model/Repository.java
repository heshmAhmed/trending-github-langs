package heshmahmed.trendinggithublangs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Repository {
    public Integer id;
    public String name;
    public String html_url;
    public String languages_url;
    public String language;
}

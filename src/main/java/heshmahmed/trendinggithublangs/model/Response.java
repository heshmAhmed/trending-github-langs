package heshmahmed.trendinggithublangs.model;

import lombok.Data;

import java.util.List;

@Data
public class Response{
    public List<Repository> items;
}
package heshmahmed.trendinggithublangs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response{
    public Integer count;
    public ArrayList<Repository> items;
}
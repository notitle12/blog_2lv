package blog;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {
    private String title;
    private String username;
    private String content;
    private String date;

}

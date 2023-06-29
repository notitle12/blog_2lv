package blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;



    //전체 게시글 목록
    @GetMapping("/posts")
    public List<PostDTO> getAllPosts() {

        List<Post> posts = postRepository.findAll();

        return (List<PostDTO>) ResponseEntity.status(HttpStatus.OK).body(posts);

    }

    //게시글 작성

    @PostMapping("/posts")
    public ResponseEntity<String> createPost(@RequestBody PostDTO postDTO) {
        // Check if token is valid
        if (!isValidToken(postDTO.getToken())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }

        String username = getUsernameFromToken(token);

        User user = userRepository.findByUsername(username);
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setUser(user);
        postRepository.save(post);

        savePostToDatabase(postDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body("Post created successfully");
    }

    private boolean isValidToken(String token) {
        // Validate the token
        // Return true if valid, false otherwise
    }

    private void savePostToDatabase(PostDTO postDTO) {
        // Save the post to the database
    }

    //선택한 게시글 조회

    @GetMapping("/posts/{postId}")
    public PostDTO getPost(@PathVariable String postId) {
            // Retrieve the selected post from the database and return it
    }

    //선택한 게시글 수정
    @PutMapping("/posts/{postId}")
    public ResponseEntity<String> updatePost(@RequestBody PostDTO postDTO) {
        // Check if token is valid
        if (!isValidToken(postDTO.getToken())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }

        // Check if the post belongs to the user
        if (!isUserPost(postDTO.getToken(), postDTO.getPostId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to update this post");
        }

        // Update the post in the database
        updatePostInDatabase(postDTO);

        return ResponseEntity.status(HttpStatus.OK).body("Post updated successfully");
    }

    private boolean isValidToken(String token) {

    }

    private boolean isUserPost(String token, String postId) {

    }

    private void updatePostInDatabase(PostDTO postDTO) {

    }

    //선택한 게시글 삭제

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable String postId, @RequestBody DeleteDTO deleteDTO) {
        // Check if token is valid
        if (!isValidToken(deleteDTO.getToken())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }

        // Check if the post belongs to the user
        if (!isUserPost(deleteDTO.getToken(), postId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to delete this post");
        }

        // Delete the post from the database
        deletePostFromDatabase(postId);

        return ResponseEntity.status(HttpStatus.OK).body("Post deleted successfully");
    }

    private boolean isValidToken(String token) {

    }

    private boolean isUserPost(String token, String postId) {

    }

    private void deletePostFromDatabase(String postId) {

    }
}

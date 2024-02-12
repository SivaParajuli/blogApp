package com.sipra.blogapplication.repo;

import com.sipra.blogapplication.models.Category;
import com.sipra.blogapplication.models.Post;
import com.sipra.blogapplication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post,Long> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
//    List<Post> findByTitleContaining(String keyword);
    @Query("select p from Post p where p.title like :k")
    List<Post> findPostByTitle(@Param("k") String keyword);

}

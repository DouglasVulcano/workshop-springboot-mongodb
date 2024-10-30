package com.douglas.workshopmongodb.config;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.douglas.workshopmongodb.domain.Post;
import com.douglas.workshopmongodb.domain.User;
import com.douglas.workshopmongodb.dto.AuthorDto;
import com.douglas.workshopmongodb.dto.CommentDto;
import com.douglas.workshopmongodb.repositories.PostRepository;
import com.douglas.workshopmongodb.repositories.UserRepository;

@Configuration
public class Instatiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(List.of(maria, alex, bob));

        Post post1 = new Post(null,
                sdf.parse("21/03/2018"),
                "Partiu viagem",
                "Vou viajar para São Paulo. Abraços!",
                new AuthorDto(maria));

        Post post2 = new Post(null,
                sdf.parse("23/03/2018"),
                "Bom dia",
                "Acordei feliz hoje!",
                new AuthorDto(maria));

        CommentDto c1 = new CommentDto("Boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDto(alex));
        CommentDto c2 = new CommentDto("Aproveite!", sdf.parse("22/03/2018"), new AuthorDto(bob));
        CommentDto c3 = new CommentDto("Tenha um ótimo dia!", sdf.parse("23/03/2018"), new AuthorDto(alex));

        post1.getComments().addAll(List.of(c1, c2));
        post2.getComments().addAll(List.of(c3));

        postRepository.saveAll(List.of(post1, post2));

        maria.getPosts().addAll(List.of(post1, post2));

        userRepository.save(maria);
    }
}

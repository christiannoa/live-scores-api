package com.chris.livescores.web;

import com.chris.livescores.domain.Game;
import com.chris.livescores.domain.Team;
import com.chris.livescores.repo.InMemoryRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GameController {
    private final InMemoryRepo repo;

    public GameController(InMemoryRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/teams")
    @Cacheable("teams")
    public List<Team> getAllTeams() {
        return repo.getAllTeams();
    }

    @GetMapping("/games/today")
    @Cacheable("gamesToday")
    public List<Game> getGamesToday() {
        return repo.getGamesToday();
    }

    @GetMapping("/games/{id}")
    @Cacheable(cacheNames = "gameById", key = "id")
    public ResponseEntity<Game> getGameById(@PathVariable String id) {
        return repo.getTeamById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}

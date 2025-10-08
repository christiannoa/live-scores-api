package com.chris.livescores.web;

import com.chris.livescores.domain.Game;
import com.chris.livescores.domain.Team;
import com.chris.livescores.repo.InMemoryRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class GameController {
    private final InMemoryRepo repo;

    public GameController(InMemoryRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/teams")
    public List<Team> getAllTeams() {
        return repo.getAllTeams();
    }

    @GetMapping("/games")
    public List<Game> allGames() {
        return repo.getAllGames();
    }

    @GetMapping("/games/today")
    public List<Game> getGamesToday() {
        return repo.getGamesToday();
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable String id) {
        return repo.getTeamById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** Reset one game by id */
    @PostMapping("/games/{id}/reset")
    public ResponseEntity<Game> resetGame(@PathVariable String id) {
        try {
            Game g = repo.resetGame(id);
            return ResponseEntity.ok(g);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /** Reset all games */
    @PostMapping("/games/reset")
    public ResponseEntity<Void> resetAll() {
        repo.resetAllGames();
        return ResponseEntity.noContent().build();
    }

}

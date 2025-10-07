package com.chris.livescores.repo;

import com.chris.livescores.domain.Game;
import com.chris.livescores.domain.Team;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class InMemoryRepo {
    private final Map<String, Team> teams = new ConcurrentHashMap<>();
    private final Map<String, Game> games = new ConcurrentHashMap<>();

    public InMemoryRepo() {
        // Initialize with some sample data
        Team heat = new Team("t1", "Heat", "Miami", "NBA");
        Team knicks = new Team("t2", "Knicks", "New York", "NBA");
        teams.put(heat.getId(), heat);
        teams.put(knicks.getId(), knicks);

        Game game1 = new Game("1", heat, knicks, 0, 0, LocalDateTime.now().minusMinutes(1));
        games.put(game1.getId(), game1);

        Game game2 = new Game("2", heat, knicks, 108, 104, LocalDateTime.now().minusDays(1));
        games.put(game2.getId(), game2);
    }

    public List<Team> getAllTeams() {
        return new ArrayList<>(teams.values());
    }

    public Optional<Game> getTeamById(String id) {
        return Optional.ofNullable(games.get(id));
    }

    public List<Game> getAllGames() {
        return new ArrayList<>(games.values());
    }

    public List<Game> getGamesToday() {
        LocalDate today = LocalDate.now();
        {
            return games.values().stream()
                    .filter(game -> game.getGameTime().toLocalDate().equals(today))
                    .collect(Collectors.toList());
        }
    }
}

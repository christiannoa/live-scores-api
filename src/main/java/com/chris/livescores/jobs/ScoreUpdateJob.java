package com.chris.livescores.jobs;

import com.chris.livescores.domain.Game;
import com.chris.livescores.repo.InMemoryRepo;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScoreUpdateJob {
    private final InMemoryRepo repo;

    public ScoreUpdateJob(InMemoryRepo repo) {
        this.repo = repo;
    }

    @Scheduled(fixedRate = 5000) // runs every 5 seconds
    public void updateScores() {
        for (Game game : repo.getAllGames()) {
            if (game.getGameTime() != null
                    && game.getGameTime().isBefore(java.time.LocalDateTime.now())
                    && (game.getStatus() == null || "SCHEDULED".equals(game.getStatus()))) {
                game.setStatus("LIVE"); // If game start time has passed → mark LIVE
            }

            // if Live, randomly increment scores
            if ("LIVE".equals(game.getStatus())) {
                game.setHomeScore(game.getHomeScore() + (int) (Math.random() * 3)); // 0–2 points
                game.setAwayScore(game.getAwayScore() + (int) (Math.random() * 3)); // 0–2 points
                // Simple logic: if total score > 20

                // after some threshold, mark FINAL
                if (game.getHomeScore() + game.getAwayScore() > 20) {
                    game.setStatus("FINISHED");
                }
            }
        }
    }
}

# 🏀 Live Scores API

A Spring Boot project that simulates **real-time sports scores**.  
Built as a learning/demo project to practice backend fundamentals such as REST APIs, scheduling, state management, and live updates.

---

## 🚀 Features

### ✅ Sprint 1: Core API + Mock Data

- Domain models for **Team** and **Game**.
- In-memory repository (`InMemoryRepo`) seeded with sample teams and games.
- Endpoints:
  - `GET /api/teams` → return all teams - 'curl -s http://localhost:8080/api/teams | jq .'
  - `GET /api/games/today` → return today’s games - 'curl -s http://localhost:8080/api/games/today | jq .'
  - `GET /api/games/{id}` → return game details by ID - 'curl -s http://localhost:8080/api/games/1 | jq .'
- Verified with `curl` commands and documented in this README.

---

### ⚡ Sprint 2: Live Score Simulation

- Added a scheduled job (`ScoreUpdateJob`) that runs every **5 seconds**.
- Games progress through states:
  - `SCHEDULED` → `LIVE` → `FINAL`
- While `LIVE`, scores update automatically with random increments.
- Real-time behavior can be observed by repeatedly hitting the `/api/games/{id}` endpoint.

---

### 🔄 Reset Feature

- Added reset endpoints to restart games without rebooting the app:
  - `POST /api/games/{id}/reset` → reset a single game -
    'curl -s http://localhost:8080/api/games/1 | jq .'
  - `POST /api/games/reset` → reset all games
    'curl -i -X POST http://localhost:8080/api/games/reset'
- Resets scores to `0–0`, sets status back to `SCHEDULED`, and schedules the game to go live again on the next tick.

---

## 🧪 How to Run

Make sure you have **Java 21+** and **Maven 3.9+** installed.

1. Clone the repository:
   ```bash
   git clone git@github.com:christiannoa/live-scores-api.git
   cd live-scores-api
   ```

(ns clj-blackjack.main
  (:require [clj-blackjack.game :refer [new-game game-cards-to-show add-new-card-in-game]]))

(println "Enter with player name:")
(def player-game (new-game (read-line)))
(def dealer-game (new-game "Dealer"))

(println "Welcome to the blackjack game " (:name player-game))

(defn display-cards [game]
  (println "Player: " (:name game))
  (println "Cards: " (game-cards-to-show game))
  (println "Points: " (:points game))
  (println "--------------------------------------"))

(defn player-decision-continue? [game]
  (display-cards game)
  (println "More one card? (y/n)")
  (= (read-line) "y"))

(defn dealer-decision-continue? [player-game dealer-game]
  (let [dealer-points (:points dealer-game)
        player-points (:points player-game)
        decision-points (if (> player-points 21) 21 player-points)]
    (< dealer-points decision-points)))

(defn play [game decision?]
  (if (decision? game)
    (let [new-game (add-new-card-in-game game)
          new-points (:points new-game)]
      (if (< new-points 21)
          (play new-game decision?)
          new-game))
    game))

; Start the game
(def player-game-end (play player-game player-decision-continue?))
(display-cards player-game-end)

(def dealer-game-end (play dealer-game (partial dealer-decision-continue? player-game-end)))
(display-cards dealer-game-end)


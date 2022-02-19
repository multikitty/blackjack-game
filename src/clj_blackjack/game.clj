(ns clj-blackjack.game
  (:require [clj-blackjack.cards :refer [cards-points cards-to-show new-card]]))

(defn game-cards-to-show [game]
  (map cards-to-show (get game :cards)))

(defn add-new-card-in-game [game]
  (let [new-cards (conj (get game :cards) (new-card))
        points (cards-points new-cards)]
    (assoc game :cards new-cards :points points)))

(defn new-game [player-name]
  (let [cards [(new-card) (new-card)]
        points (cards-points cards)]
    {:player-name player-name
     :cards       cards
     :points      points}))

; TODO - create flow game
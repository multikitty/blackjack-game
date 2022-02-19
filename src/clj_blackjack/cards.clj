(ns clj-blackjack.cards)

; random generate numbers
(defn new-card []
  (inc (rand-int 13)))

; use to convert JQK to 10
(defn value-of-cards [card]
  (if (> card 10) 10 card))

; represent the conversion A to 11
(defn value-of-'as'-card-to-eleven [card]
  (if (= card 1) 11 card))

; A(1) can be value 1 or 11
; JQK(11,12,13) that represent by value 10
(defn cards-points [cards]
  (let [cards-converted (map value-of-cards cards)
        cards-points-with-A-one (reduce + (map value-of-cards cards))
        cards-points-with-A-eleven (reduce + (map value-of-'as'-card-to-eleven cards-converted))]
    (if (> cards-points-with-A-eleven 21) cards-points-with-A-one cards-points-with-A-eleven)))

; convert cards number in correctly 'cards' value
(defn cards-to-show [card]
  (get {1 "A", 11 "J", 12 "Q", 13 "K"} card card))


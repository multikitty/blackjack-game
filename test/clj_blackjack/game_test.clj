(ns clj-blackjack.game-test
  (:require [clojure.test :refer :all])
  (:require [clj-blackjack.game :refer :all]))

(deftest should-be-end-game-when-points-is-more-or-equals-21
  (is (end-game? {:points 21}))
  (is (end-game? {:points 22})))

(deftest should-not-be-end-game-when-points-is-less-than-21
  (not (end-game? {:points 20}))
  (not (end-game? {:points 1})))

(deftest should-have-two-cards-when-start-a-new-game
  (let [game (new-game "someone")
        cards-len (count (:cards game))]
    (is (= cards-len 2))))

(deftest should-have-name-in-game
  (let [game (new-game "someone")]
    (is (= (:name game) "someone"))))

(deftest should-add-one-new-card-in-game-and-some-new-points
  (let [game (new-game "someone")
        new-game (add-new-card-in-game game)
        game-cards-size (count (:cards game))
        new-game-cards-size (count (:cards new-game))]
    (is (= game-cards-size 2))
    (is (= new-game-cards-size 3))
    (is (not= (:points new-game) (:points game)))))
(ns clj-blackjack.cards-test
  (:require [clojure.test :refer :all])
  (:require [clj-blackjack.cards :refer [new-card value-of-cards value-of-'as'-card-to-eleven]]))

(deftest should-not-return-more-than-13-or-less-than-1-value
  (doseq [_ (range 100)]                                    ;simulate 100 times the new-card random generated
    (let [rand (new-card)]
      (is (>= rand 1))
      (is (<= rand 13)))))

(deftest should-return-10-with-values-more-than-10
  (doseq [card (range 10 100)]                              ;range start inclusive
    (let [converted (value-of-cards card)]
      (is (= converted 10)))))

(deftest should-return-the-same-value-with-values-less-than-10
  (doseq [card (range 0 11)]                                 ;range end exclusive
    (let [converted (value-of-cards card)]
      (is (= converted card)))))

(deftest should-return-eleven-when-input-value-is-one
  (is (= (value-of-'as'-card-to-eleven 1) 11)))

(deftest should-return-the-same-input-value-when-is-not-one
  (let [expected-values [11 2 10 11 -1 -10]
        values-than-need-conversion [1 2 10 11 -1 -10]]
    (is (= (map value-of-'as'-card-to-eleven expected-values) expected-values))
    (is (not= (map value-of-'as'-card-to-eleven values-than-need-conversion) values-than-need-conversion))
    (is (= (map value-of-'as'-card-to-eleven values-than-need-conversion) expected-values))))
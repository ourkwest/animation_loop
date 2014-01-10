(ns uk.me.westmacott.example.example
  (:import (uk.me.westmacott.world AbstractWorld)
           (uk.me.westmacott.animation AnimationLoop)
           (java.awt Color)))

(def a 0)

(defn tick-fn [] (def a (+ a 0.01)))

(defn render-fn [g]
  (let [x (* 200 (Math/sin a))
        y (* 200 (Math/cos a))]
    (.setColor g Color/BLACK)
    (.drawLine g 250 250 (+ 250 x) (+ 250 y))))

(def my-world
  (proxy [AbstractWorld] ["My Clojure World" 500 500]
    (tick [] (tick-fn))
    (render [g2d] (render-fn g2d))))

(-> my-world
    (AnimationLoop.)
    (.tickAt 100)
    (.renderAt 30))


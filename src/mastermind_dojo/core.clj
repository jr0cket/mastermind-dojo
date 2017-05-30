(ns mastermind-dojo.core)

;; (def row-of-colour [:blue :green :yellow :red])

;; couldnt use nth with a set :(
;;(def mastermind-colours #{:blue :green :yellow :red :orange :black :white})

(def mastermind-colours [:blue :green :yellow :red :orange :black :white])

(defn generate-colour []
  (let [max (count mastermind-colours)]
    (nth mastermind-colours (rand-int max))))

;; (generate-colour)

(defn generate-board [board-size]
  (take board-size (repeatedly generate-colour)))

(def shield (generate-board 4))

(println shield)

(def guess [:black :blue :white :red])

(defn try-guess [my-guess shield]
  (if (= my-guess shield)
    "awsome"
    "doh"))
;; => #'mastermind-dojo.core/try-guess

(try-guess shield shield)
(try-guess [:blue :green :green :blue] shield)

(map #(= %1 %2) [:blue] [:blue])
(map #(= %1 %2) [:blue] [:green])

(map #(= %1 %2) [:blue :green :red :orange] [:blue :red :green :orange])
;; => (true false false true)


(defn right-colour-wrong-place [colour]
  (let [result (filter #(= colour %1) shield)]
    (cond
      (= (list colour) result) :white-pin
      :else nil)))

(defn right-colour-right-place [colour1 colour2]
  (if (= colour1 colour2)
    :black-pin
    (right-colour-wrong-place colour1)))

;; (map right-colour-wrong-place (:black-pin :green :red :black-pin) shield)


(defn take-a-guess [guess shield]
  (map right-colour-right-place guess shield))



(filter #(= :blue %1) [:blue :red :green :purple])
(filter #(= :orange %1) [:blue :red :green :purple])


(map right-colour-right-place guess shield)


(map right-colour-right-place
     [:blue :green :red :orange]
     [:blue :red :green :orange])
;; => (:black-pin :green :red :black-pin)


(map #(= %1 %2)
     [:blue :green :red :orange]
     [:blue :red :green :orange])

;; matching algorithm
(map matched-colour
     (map #(= %1 %2)
          [:blue :green :red :orange]
          [:blue :red :green :orange]))
;; => (:black nil nil :black)




#_(defn polymorhp
  ([] (polymorhp "no args"))
  ([arg] (str "have " arg)))
;; => #'mastermind-dojo.core/polymorhp


#_(defn generate-board [board-size]
    (dotimes [colour board-size]
      (generate-colour)))

(ns forca.core
  (:gen-class))

;; Print in the screen the status of the game
(defn print-status [lifes word right-letters]
  (println "Vidas " lifes)
  (doseq [letra (seq word)]
         (if (contains? right-letters (str letra)) (print letra " ") (print "_ ")) )(println))

;; Player's initial number of life
(def number-of-lifes 5)

;; Function called when lifes come to zero
(defn you-lose [] (print "You lose"))

;; Function player hits all letters in the word
(defn you-won [] (print "You won"))

;; Get the remaining letters to hit
(defn remaining-letters [word right-letters] 
  (remove (fn [letter] (contains? right-letters (str letter))) word))

;; Test if player hit all letters in the word
(defn hit-all? [word right-letters] 
  (empty? (remaining-letters word right-letters)))

;; Test if player guess is in the word
(defn hit-one? [word guess]
  (.contains word guess ))

;; Just read the pressed key
(defn read-key! [] (read-line))

;; Game function, called on main
(defn game [lifes word right-letters]
  (print-status lifes word right-letters)
  (cond (= lifes 0)
        (you-lose)
        (hit-all? word right-letters) (you-won)
        :else
        (let [guess (read-key!)]
          (if (hit-one? word guess)
            (do
              (println "Right")
              (recur lifes word (conj right-letters guess)))
            (do
              (println "Wrong")
              (recur (dec lifes) word right-letters))))))

;; Main function
(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (do
    (println "Lets get started! Guess the word I am thinking of!")
    (game number-of-lifes "CLOJURE" #{})))
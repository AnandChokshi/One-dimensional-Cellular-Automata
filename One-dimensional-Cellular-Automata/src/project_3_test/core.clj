(ns project-3-test.core)

(defn getNextRow
  "Function for getting next row"
  [rule currentRow]
  (loop [currentRow (bit-shift-left currentRow 1) nextRow 2r0 bitPos 1]
    (if (< bitPos 32)
      (if (bit-test rule (bit-and 7 currentRow))
        (recur (bit-shift-right currentRow 1) (bit-set nextRow bitPos) (inc bitPos))
        (recur (bit-shift-right currentRow 1) nextRow (inc bitPos)))
      (bit-shift-right nextRow 1))))

(defn printRow
  "Function for printing row"
  [nextRow]
  (doseq [x (reverse (range 31))]
    (if(bit-test nextRow x)
      (print 1)
      (print " ")))
  (println))

(defn automata
  "Function which provides automata"
  [rule currentRow times]
  (dorun (map printRow (take times (iterate (fn [a] (getNextRow rule a)) currentRow)))))

(take 16 (iterate (fn [a] (getNextRow 30 a)) 32768))
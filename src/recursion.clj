(ns recursion)

(defn product [coll]
  (if (empty? coll)
    1
    (* (first coll)
       (product (rest coll)))))

;(product [1 2 4])
;(product '(1 2 3 4))
; starts with oh not empty so gets to first coll and rest with *
; then again sees not empty
;=   (product (cons 1 (cons 2 (cons 3 (cons 4 '())))))
;=> (* 1 (product (cons 2 (cons 3 (cons 4 '())))))
;=> (* 1 (* 2 (product (cons 3 (cons 4 '())))))
;=> (* 1 (* 2 (* 3 (product (cons 4 '())))))
;=> (* 1 (* 2 (* 3 (* 4 (product '())))))
; finally sees oh empty then returns 1
;=> (* 1 (* 2 (* 3 (* 4 1))))        ; (empty? '()) is true, so (product '()) ;=> 1
; once reaches base then the expression evaluation starts
;=> (* 1 (* 2 (* 3 4)))
;=> (* 1 (* 2 12))
;=> (* 1 24)
;=> 24

(defn singleton? [coll]
  (boolean (and (seq coll)
                (or (first (seq coll))
                    (nil? (first (seq coll))))
                (empty? (rest (seq coll))))))

(defn my-last [coll]
  (if (or (empty? coll) (singleton? coll))
    (first coll)
    (my-last (rest coll))))

(defn max-element [a-seq]
  (if (empty? a-seq)
    nil
    (if (singleton? a-seq)
      (first a-seq)
      (max (first a-seq)
           (max-element (rest a-seq))))))

(defn seq-max [seq-1 seq-2]
  (let [count-seq-1 (count seq-1)
        count-seq-2 (count seq-2)]
    (if (== count-seq-1 (max count-seq-1 count-seq-2))
      (if (== count-seq-1 count-seq-2) seq-2 seq-1)
      seq-2)))

(defn longest-sequence [a-seq]
  (if (empty? a-seq)
    nil
    (seq-max (first a-seq)
             (longest-sequence (rest a-seq)))))


(defn my-filter [pred? a-seq]
  (if (empty? a-seq)
    a-seq
    (if (pred? (first a-seq))
      ; this shows that the recursion call is the last one always evaluated with the base
      (cons (first a-seq)
            (my-filter pred? (rest a-seq)))
      (my-filter pred? (rest a-seq)))))


(defn sequence-contains? [elem a-seq]
  (cond
    (empty? a-seq) false
    (= elem (first a-seq)) true
    :else
    (sequence-contains? elem (rest a-seq))))

(defn my-take-while [pred? a-seq]
  (cond
    (empty? a-seq) '()
    (pred? (first a-seq))
    ; this shows that the recursion call is the last one always evaluated with the base
      (cons (first a-seq)
             (my-take-while pred? (rest a-seq)))
    :else
      '()))

(defn my-drop-while [pred? a-seq]
  (cond
    (empty? a-seq) '()
    (pred? (first a-seq)) (my-drop-while pred? (rest a-seq))
    :else
      a-seq))


(defn seq= [a-seq b-seq]
  (cond
    (and (empty? a-seq) (empty? b-seq)) true
    (not (= (count a-seq) (count b-seq))) false
    (not (= (first a-seq) (first b-seq))) false
    :else
      (seq= (rest a-seq) (rest b-seq))))

(defn my-map [f seq-1 seq-2]
  (cond (or (empty? seq-1) (empty? seq-2)) '()
        :else
        (cons (f (first seq-1) (first seq-2))
              (my-map f (rest seq-1) (rest seq-2)))))


(defn power [n k]
  (if (zero? k)
    1
    (* n
       (power n (dec k)))))

(defn fib [n]
  (if (< n 2)
    n
    (+ (fib (- n 1))
       (fib (- n 2)))))

(defn my-repeat [how-many-times what-to-repeat]
  (if (< how-many-times 1)
    '()
    (cons what-to-repeat
          (my-repeat (dec how-many-times) what-to-repeat))))

(defn my-range [up-to]
  (if (zero? up-to)
    '()
    (cons (dec up-to)
          (my-range (dec up-to)))))

(defn tails [a-seq]
  (if (empty? a-seq)
    [[]]
    (cons a-seq
          (tails (rest a-seq)))))

(defn inits [a-seq]
  (if (empty? a-seq)
    [[]]
    (map reverse (cons (reverse a-seq)
                       (tails (rest (reverse a-seq)))))))

(defn rotations-helper [counted a-seq]
  (cond
    (empty? a-seq) '(())
    (== 0 counted) nil
    :else
    (let [new-seq (concat (rest a-seq) (vector (first a-seq)))]
      (cons new-seq
            (rotations-helper (dec counted) new-seq)))))

(defn rotations [a-seq]
  (rotations-helper (count a-seq) a-seq))

(defn count-elem-helper [n elem coll]
  (if (empty? coll)
    n
    (let [new-count (if (= elem (first coll))
                      (inc n)
                      n)]
      (count-elem-helper new-count
                         elem
                         (rest coll)))))

(defn count-elem [elem coll]
  (count-elem-helper 0 elem coll))

(defn my-frequencies-helper [freqs a-seq]
  (if (empty? a-seq)
    freqs
    (apply merge (map
                   (fn [x] {x (count-elem x a-seq)})
                   (set a-seq)))))

(defn my-frequencies [a-seq]
  (my-frequencies-helper {} a-seq))

(defn un-frequencies [a-map]
  (if (empty? a-map)
    ()
    (concat (repeat (second (first a-map)) (first (first a-map)))
          (un-frequencies (rest a-map)))))

(defn my-take [n coll]
  [:-])

(defn my-drop [n coll]
  [:-])

(defn halve [a-seq]
  [:-])

(defn seq-merge [a-seq b-seq]
  [:-])

(defn merge-sort [a-seq]
  [:-])

; Encore Stuff - later

(defn split-into-monotonics [a-seq]
  [:-])

(defn permutations [a-set]
  [:-])

(defn powerset [a-set]
  [:-])


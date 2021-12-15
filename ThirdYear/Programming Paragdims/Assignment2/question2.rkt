#lang scheme
;;question2.rkt - Author Daniel Hannon(19484286)
;;Q2 Part A
(define (ins_beg val_1 val_2)
  ;;I have to define val_1 as a list
  ;;within this or it does not work
  (append (list val_1) val_2))
;;Q2 Part B
(define (ins_end val_1 val_2)
  ;;I have to define val_1 as a list
  ;;within this or it does not work
  (append val_2 (list val_1)))

;;Q2 Part C
(define (cout_top_level x)
  ;;Count the number of top levels
  (if (empty? x)
      0
      (+ 1 (cout_top_level (cdr x)))
      )
  )

;;Q2 Part D
(define (count_instances val list)
  ;;count instances
  (if (empty? list)
      0
      (if (= val (car list))
          (+ 1 (count_instances val (cdr list)))
          (+ 0 (count_instances val (cdr list)))
          )
      )
  )

;;Q2 Part E
;;wrapper for the function
(define (count_instances_tr val list)
  (count_instances_tr_inner val list 0)
)

(define (count_instances_tr_inner val list tally)
  (if (empty? list)
      ;;returns tally if the list is fully traversed
      tally
      (if (= val (car list))
          ;;If it is true the tally is increased by one, otherwise it remains the same
          (count_instances_tr_inner val (cdr list) (+ tally 1))
          (count_instances_tr_inner val (cdr list) tally)
          )
      )
  )

;;Q2 Part F
(define (count_instances_deep val list)
  ;;flatten turns any deep list into a flat one :)
  ;;Its a built in function in scheme
  (count_instances_tr_inner val (flatten list) 0)
  )

;;Tests
(printf "Testing ins_beg\n")
(ins_beg 'a '(b c d))
(ins_beg '(a b) '(b c d))
(printf "Testing ins_end\n")
(ins_end 'a '(b c d))
(ins_end '(a b)  '(b c d))
(printf "Testing cout_top_level\n")
;; should return 3
(cout_top_level '(a b c))
;; should return 4
(cout_top_level '((a b) c d ((e) f)))
;;should return 1
(cout_top_level '((a(b(c(d))))))

(printf "Testing count_instances\n")
;;Should return 3
(count_instances 7 '(7 7 5 7))
;;Should return 2
(count_instances 2 '(2 0 0 2 0 0))

(printf "Testing count_instances_tr\n")
;;Should return 3
(count_instances_tr 7 '(7 7 5 7))
;;Should return 2
(count_instances_tr 2 '(2 0 0 2 0 0))

(printf "testing count_instances_deep\n")
(count_instances_deep 7 '((1 7) 4 6 ((7) 2)))
(count_instances_deep 7 '((1 7 ( 7 (0 (1 2 (6 7)))) 7)))
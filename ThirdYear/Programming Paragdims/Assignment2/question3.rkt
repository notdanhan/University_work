#lang scheme
;;question3.rkt Author - Daniel Hannon (19484286)

;;Q3 Part A
(define (btree_traverse btree)
  (if (empty? btree)
      (printf "")
      (begin
        ;;traverse left
        (btree_traverse (car btree))
        ;;print node value
        (print (cadr btree))
        (printf ", ")
        ;;traverse right
        (btree_traverse (caddr btree))
       )
  )
)

;;Q3 Part B
(define (btree_search val btree)
  (if (empty? btree)
      #f
      (cond
        ;;check if equal to the value stored at the node
        [(= (cadr btree) val) #t]
        ;;Check if less than the value stored at the node,
        ;;as a binary search tree is ordered, only check if it is less than
        [(> (cadr btree) val) (btree_search val (car btree))]
        ;;Check if greater than the value stored at the node
        [(< (cadr btree) val) (btree_search val (caddr btree))]
        )
      )
  )

;;Q3 Part C
(define (btree_insert val btree)
  (if (empty? btree)
      (list '() val '()) 
      (cond
        ;;IF GEQ node value - Insert Right of node
        ;;IF LT node value - Insert Left of node
        [(<= (cadr btree) val) (list (car btree) (cadr btree) (btree_insert val (caddr btree)))]
        [(> (cadr btree) val) (list (btree_insert val (car btree)) (cadr btree) (caddr btree))]
        )
      )
  )

;:Q3 Part D

(define (list_to_btree list)      
  (list_to_btree_inner list '())
  )

(define (list_to_btree_inner list btree)
  (if (empty? list)
      btree
      (list_to_btree_inner (cdr list) (btree_insert (car list) btree))
      )
  )

;;Q3 Part E
(define (tree_sort_list list)
  ;;Technically The solution to D does this
  (btree_traverse (list_to_btree list))
  )


;;Discriminator functions for Q3 Part F

;;Sort in Ascending order
(define (ascending_sort x y)
  (cond
    [(= x y) 0]
    [(> x y) 1]
    [(< x y) -1]
    )
  )

;;Sort in Descending order
(define (descending_sort x y)
  (ascending_sort y x)
  )

;;Ascending Least Significant Digit
(define (ascending_lsd x y)
  (ascending_sort (modulo x 10) (modulo y 10))
  )

;;Higher Order Binary Tree Insert Function as per Q3 Part F
(define (higher_order_btree_insert val btree discrim)
  (if (empty? btree)
      (list '() val '())
      (cond
        ;;Evaluate Discriminator and act in accordance to it
        ;;GEQ insert to the right
        ;;LT insert to the left
        [(<= (discrim (cadr btree) val) 0)
         (list (car btree) (cadr btree) (higher_order_btree_insert val (caddr btree) discrim))
         ]
        [(> (discrim (cadr btree) val) 0)
         (list (higher_order_btree_insert val (car btree) discrim) (cadr btree) (caddr btree))
         ]
        )
      )
  )

;;Companion Functions for Q3 Part F for QOL while testing
(define (list_to_btree_higher_order list discrim)
  (list_to_btree_higher_order_inner list '() discrim)
  )

(define (list_to_btree_higher_order_inner list btree discrim)
  (if (empty? list)
      btree
      (list_to_btree_higher_order_inner (cdr list) (higher_order_btree_insert (car list) btree discrim) discrim)
      )
  )

;;Tests
;;Part A
(printf "Testing btree traverse\n")
(btree_traverse '(((() 1 ()) 2 (() 3 ())) 4 ((() 5 ()) 6 (() 7 ()))))
;;Part B
(printf "\ntesting btree search\n")
;;Return True
(btree_search '4 '(((() 1 ()) 2 (() 3 ())) 4 ((() 5 ()) 6 (() 7 ()))))
;:Return False
(btree_search '8 '(((() 1 ()) 2 (() 3 ())) 4 ((() 5 ()) 6 (() 7 ()))))
;;Part C
(printf "testing btree_insert\n")
(btree_traverse (btree_insert '8 '(((() 1 ()) 2 (() 3 ())) 4 ((() 5 ()) 6 (() 7 ())))))
;;Part D
(printf "\ntesting creating a btree from a list\n")
(btree_traverse (list_to_btree '(4 6 7 3 5 2 4 5 11 23 45 67 13 9 1 0)))
;;Part E
(printf "\nTesting Sorting a list using a binary tree\n")
(tree_sort_list '(9 8 7 6 5 4 3 2 1))
;;Part F
(printf "\nTesting Higher Order btree\n")
(printf "Ascending order\n")
(btree_traverse (list_to_btree_higher_order '(4 3 5 1 2 6 7) ascending_sort))
(printf "\nDescending order\n")
(btree_traverse (list_to_btree_higher_order '(4 3 5 1 2 6 7) descending_sort))
(printf "\nAscending Least Significant Digit\n")
(btree_traverse (list_to_btree_higher_order '(12 72 63 85 94 21 9) ascending_lsd))
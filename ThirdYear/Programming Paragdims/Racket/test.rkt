#lang scheme
(cadddr '(a b c d e f))
(caar '((a b)3(d e)))
(cadar '((a b)3(d e)))
(caaddr '((a b)3(d e)))

(car (cons 'x '(y z a b))) 
(cdr (cons 'a '(x y z w)))

(cons (cdr '(a b c)) (cdr '(b c d)))
(cons (car '(a b c)) (cdr '(b c d)))
(cons '(car '(a b)) '(and orange))
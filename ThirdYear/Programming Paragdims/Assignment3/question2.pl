contains1(X,Y) :-
  [H|_] = X,
  Y = H.

contains2([_|X],Y) :-
  X = Y.

insertInOrder(A,[],C):-
  cons([],[A],C).
insertInOrder(A,B,C):-
  [H|T] = B,
  (A < H->cons([A],B,C);insertInOrder(A,T,D),cons([H],D,C)).

cons([],L,L).
cons([H|T],L2,[H|Y]):-
  cons(T,L2,Y).

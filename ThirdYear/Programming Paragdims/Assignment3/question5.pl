reverseList([],[]).
reverseList([H|T],Y) :-
  reverseList(T,A),
  cons(A,[H],Y).

cons([],L,L).
cons([H|T],L2,[H|Y]):-
  cons(T,L2,Y).

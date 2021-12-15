mergeLists([H|T],List2,List3,[H|Output]) :-
  mergeLists(T,List2,List3,Output).
mergeLists([],[H|T],List3,[H|Output]) :-
  mergeLists([],T,List3,Output).
mergeLists([],[],[H|T],[H|Output]) :-
  mergeLists([],[],T,Output).
  %I Don't know how to get rid of '_' from the end of the list, I've tried several things
  %but nothing worked
mergeLists([],[],[],[Output]).

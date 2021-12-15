isNotElementInList(E,List) :-
  ((List = []) ->
    1=1
    ;
    [H|T] = List,
    E =\= H,
    isNotElementInList(E,T)).

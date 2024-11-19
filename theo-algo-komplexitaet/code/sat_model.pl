% Fakten
tf(true).
tf(false).

t(true).
f(false).

% (A ∨ ¬B) ∧ (B ∨ C)
a(A,B,C) :- 
    (t(A); f(B)),(t(B); t(C)).

% (A ∨ ¬B ∨ ¬D) ∧ (C ∨ ¬D) ∧ ¬A
b(A,B,C,D) :- 
    (t(A); f(B); f(D)),
    (t(C); f(D)),
    f(A) .

% (CNF) (A ∨ ¬B ∨ C) ∧ (¬A ∨ B) ∧ (¬C ∨ D)
f(A, B, C, D) :-
    (t(A); f(B); t(C)), 
    (f(A); t(B)), 
    (f(C); t(D)).

% (A ∨ ¬B) ∧ (¬A ∨ B) ∧ (¬A ∨ ¬C) ∧ (C ∨ D) ∧ (¬C ∨ ¬D)
g(A, B, C, D) :-
    (t(A); f(B)), 
    (f(A); t(B)), 
    (f(A); f(C)), 
    (t(C); t(D)), 
    (f(C); f(D)).

% Wenn man es "schön" haben möchte:
:- op(200, fy, ¬).
:- op(300, xfy, ∧).
:- op(400, xfy, v).
¬(false).
v(A,B) :- A; B,!.
∧(A,B) :- A, B,!.

l(A,B,C) :- 
    (A v ¬(B)) ∧ (B v C).

c(A, B, C, D) :-
    (A v ¬(B)) ∧
    (¬(A) v B) ∧
    (¬(A) v ¬(C)) ∧ 
    (C v D) ∧
    (¬(C) v ¬(D)).

/*
Example interaction:

# Loading the file:
/Applications/SWI-Prolog.app/Contents/MacOS/swipl sat_model.pl 

# At the command line:

?- tf(A),tf(B),tf(C),tf(D),c(A,B,C,D).

*/
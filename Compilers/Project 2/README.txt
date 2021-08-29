My program works by calling CLexicalAnalyzer class and gathering the
tokens of target file. It stores the token<String> in an ArrayList 
and the sent to the CGrammarParser class which looks at the grammar
and determines if the tokens are *cool* :) This is tracked by a boolean
"answer" and that if any logic is incorrect this will be swapped to False,
when at false the Parser can't go anywhere except for returning False 
to Controller class indicating a rejected string.

The beautiful grammar I used (I changed the names of all the things
to make it look better, easier to read and write in program): 

A    -> tiE | viE
B    -> tiE | viE | @
E    -> ;B | [n];B | (G){KL}B
G    -> tiIH | vS
S    -> iIH | @
H    -> ,F | @
F    -> tiIH | viIH
I    -> [] | @
K    -> tiDK | viDK | @
D    -> ; | [n];
L    -> ML | @
M    -> R; | ; | {KL} | f(R)MO | w(R)M | rQ
O    -> eM | @
Q    -> ; | R;   
R    -> iN | nXVT | (R)XVT 
N    -> =R | [R]P | ()XVT | (RC)XVT | XVT
P    -> =R | XVT
T    -> <=ZXV | <ZXV | >ZXV | >=ZXV | ==ZXV | !=ZXV | @
V    -> +ZXV | -ZXV | @
X    -> *ZX | /ZX | @
Z    -> (R) | iJ | n
J    -> [R] | () | (RC) | @
C    -> ,RC | @

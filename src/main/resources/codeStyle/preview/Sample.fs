( numbers and math operations)
3 4 + .
12 12 / .
1360 23 - .
17 4 mod .

( string )
." regular forth string"

( constants )
false false and
true false =

( keywords and compile block )
: less17 ( n -- n ) dup 17 > if ." Greater than 17!" else ." Less than 17!" then ;

( compile mod block )
: sqaure dup * ;

( stack operations )
dup
.
rot 3
drop
.s
2 swap

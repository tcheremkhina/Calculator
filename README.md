# Calculator
This code library can parse expressions with variables and evaluate them relative to given values.

Operations:  
Binary:  
`+ - * /`  
`min`  
`max`  
Unary:  
`-`  
`count` (counts 1 in binary interpretation)  

Variables must be named `x` `y` `z` or consist of more than one lowercase English letters. Of course variable names can't be same as function names.  

In case of an error in calculation process (divide by zero of overflow) result is `null`.  

In `src/expression/Main.java` there is an example of using parser + evaluate mechanism.

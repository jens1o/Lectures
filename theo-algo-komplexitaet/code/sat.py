class Expr:

    def is_solution(self, binding : dict[str,bool]):
        """ True or False if this expression definitively 
            evaluates to the respective truth value with the 
            given binding, which does not necessarily
            require all truth values to be bound.
            None if the definitive truth value cannot be 
            determined with the given binding. E. g., if 
            this expression represents a variable for which 
            the binding has no value, None is returned.

            An expression such as "A ⋀ B" would return True 
            if A and B are both True in the 
            binding, False if at least one of them is bound 
            to False, and None otherwise.
        """
        pass


class And(Expr):
    def __init__(self, exprs):
        self.exprs = exprs

    def is_solution(self, binding):
        r = True
        for expr in self.exprs:
            e = expr.is_solution(binding)
            if e is None:
                r = None  # the result may still be true
            elif not e:
                return False
        return r

    def __str__(self):
        return " ⋀ ".join(str(expr) for expr in self.exprs)


class Or(Expr):
    def __init__(self, exprs):
        self.exprs = exprs

    def is_solution(self, binding):
        r = False
        for expr in self.exprs:
            e = expr.is_solution(binding)
            if e is None:
                r = None  # the result maybe true
            elif e:
                return True
        return r

    def __str__(self):
        return " ⋁ ".join(str(expr) for expr in self.exprs)


class Not(Expr):
    def __init__(self, expr):
        self.expr = expr

    def is_solution(self, binding):
        e = self.expr.is_solution(binding)
        if e is None:
            return None
        else:
            return not e

    def __str__(self):
        return f"¬{self.expr}"


class Var(Expr):
    def __init__(self, name):
        self.name = name

    def is_solution(self, binding):
        """ True or False if bound.
            None if unbound (default). 
        """
        if self.name not in binding:
            return None
        else:
            return binding[self.name]

    def __str__(self):
        return self.name


A = Var("a")
B = Var("b")
C = Var("c")
D = Var("d")
vars = [A, B, C, D] 
""" The variables are now indexed to enable iterating over 
    them in the solve function. """

expr = And(
    [
        Or([A, Not(B)]),
        Or([Not(A), B]),
        Or([Not(A), Not(C)]),
        Or([C, D]),
        Or([Not(C), Not(D)]),
    ]
)
print("Finding solutions for: " + str(expr))

solution : dict[str,bool] = {} 
""" Stores the current solution by mapping the name of the 
    variable to its current truth value."""

def solve(expr, vars, nextVar=0):
    for v in [True,False]: # try both values for the variable
        solution[vars[nextVar].name] = v
        e = expr.is_solution(solution)
        print("Trying: " + str(solution) + " => " + str(e))
        if e is None: # continue with the next variable
            solve(expr, vars, nextVar + 1)
        elif e:
            print("Solution found: " + str(solution))
    
    del solution[vars[nextVar].name] # enable backtracking

solve(expr, vars)

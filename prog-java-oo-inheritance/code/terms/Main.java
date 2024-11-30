class MathException extends Exception { // checked exception
    public MathException(String message) {
        super(message);
    }
}

abstract class Term {
    private int priority;
    private Term(int priority) {
        this.priority = priority;
    }

    abstract int evaluate() throws MathException;
    final public int getPriority(){return priority;} // Int.MAX_VALUE for Number, 1 for Plus, 2 for Division
    abstract public String toString();
}

class Plus extends Term {
    private Term term1;
    private Term term2;

    public Plus(Term term1, Term term2) {
        super(1);
        this.term1 = term1;
        this.term2 = term2;
    }

    int evaluate() throws MathException {
        return term1.evaluate() + term2.evaluate();
    }

    public String toString() {
        return term1.toString() + " + " + term2.toString();
    }
}

class Division extends Term {
    private Term term1;
    private Term term2;

    public Division(Term term1, Term term2) {
        super(2);
        this.term1 = term1;
        this.term2 = term2;
    }
    int evaluate() throws MathException {
        if (term2.evaluate() == 0) {
            throw new MathException("Division by zero");
        }
        return term1.evaluate() / term2.evaluate();
    }

    public String toString() {
        String t2 = term2.toString();
        String t1 = term1.toString();
        if (term1.getPriority() < this.getPriority()) {
            t1 = "(" + t1 + ")";
        }
        if (term2.getPriority() < this.getPriority()) {
            t2 = "(" + t2 + ")";
        }

        return t1 + " / " + t2;
    }
}

class Number extends Term {
    private int value;

    public Number(int value) {
        super(Integer.MAX_VALUE);
        this.value = value;
    }

    public int evaluate() {
        return value;
    }

    public String toString() {
        return Integer.toString(value);
    }
}


void main(String[] args) {
    try {
        System.out.println(new Division(new Number(1), new Division(new Number(2), new Number(1))));
        System.out.println(new Division(new Number(1), new Plus(new Plus(new Number(1),new Number(2)), new Number(1))));
        
        Term term1 = new Plus(new Number(1), new Division(new Number(2), new Number(1)));
        System.out.println(term1 + " = " + term1.evaluate());
        Term term2 = new Plus(new Number(1), new Division(new Number(2), new Number(0)));
        System.out.println(term2 + " = " + term2.evaluate());
    } catch (MathException e) {
        System.out.println(e.getMessage());
    }
}
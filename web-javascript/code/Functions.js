// The last examples require that "use strict"; is not enabled!

// the function (see below) is hoisted, so it can be called before it is defined
hello("Michael");

function hello(person = "World") {
  // argument with default value
  console.log(`fun: Hello ${person}!`);
}

// helloExpr(); // the variable declaration is hoisted, but not the definition!
// So it cannot be called here!
var helloExpr = function () {
  console.log("expr: Hello World!");
};

// Arrow Functions
const times3 = (x) => x * 3;
console.log("times3(5)", times3(5)); // 15
const helloArrow = () => console.log("arrow: Hello World!");
const helloBigArrow = () => {
  const s = "Hello World!";
  console.log("arrow: " + s);
  return s;
};

console.log("Hello World!");
helloExpr();
helloArrow();

var helloXXX = function helloXXX() {
  // Function Expression with (useless) Name
  // console.log(arguments); // arguments is an array-like object
  console.log(`Hello: `, ...arguments);
};
helloXXX("Michael", "John", "Jane");

function sum(...args) {
  // rest parameter
  console.log("args: " + args);
  process.stdout.write("...args: ");
  console.log(...args); // we use the spread operator here
  return args.reduce((a, b) => a + b, 0); // function nesting
}
console.log(sum(1, 2, 3, 4, 5)); // 15
console.log(sum());

/* Generator Functions */
function* fib() {
  // generator
  let a = 0,
    b = 1;
  while (true) {
    yield a;
    [a, b] = [b, a + b];
  }
}
const fibGen = fib();
console.log(fibGen.next().value); // 0
console.log(fibGen.next().value); // 1
console.log(fibGen.next().value); // 1
console.log(fibGen.next().value); // 2
/* Will cause an infinite loop: for (const i of fib()) console.log(i);
   // 0 1 1 2 3 5 8 13 21 34 55 89 144 233 377 ... */

/** Partial function application */

function add(x, y) {
  return x + y;
}

const add2 = add.bind(null, 2); // the "null" is the value of "this"
console.log(add2(3));

/** "this" in functions */
console.log('"this" in functions:');

function f(c) {
  const that = this;

  function f() {
    return this === that;
  }

  const fExpr = () => {
    return this === that;
  };

  console.log(" globalThis === that: " + (globalThis == this));
  console.log(" this === that (function): " + f());
  console.log(" this === that (function expression): " + fExpr());
}
f();

globalThis.x = -1;
this.x = 0;
console.log(
  "this.x: " + this.x + "(globalThis === this: " + (globalThis === this) + ")",
);

function addToValue(b) {
  return this.x + b;
}
console.log(addToValue(1));
console.log(addToValue.call(this, -101));

const obj = { x: 101, addToValue: addToValue };
console.log("obj.addtoValue(-101): " + obj.addToValue(-101));

const add100 = addToValue.bind({ x: 100 });
console.log("add100: " + add100(100));

console.log("Done.");

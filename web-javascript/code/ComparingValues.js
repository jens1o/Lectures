'use strict';
const Queue = require('./Queue');

const messages = new Queue();

function log(message, ...args) {
    messages.enqueue([message]);
    messages.enqueue(args);
}

// Gleichheit       ==      // mit Typumwandlung (auch bei <, >, <=, >=)
// Ungleichheit     !==
// strikt gleich    ===     // ohne Typumwandlung
// strikt ungleich  !===

log('1 == "1": ', 1 == "1");
log('1 === "1": ', 1 == "1");
log('1.0 == 1: ', 1 == 1.0);
log('1 === 1n: ', 1 == 1n);

log('null == NaN: ', null == NaN);
log('null == NaN: ', null == NaN);
log('null == null: ', null == null);
log('null === null: ', null === null);
log('undefined == undefined: ', undefined == undefined);
log('undefined === undefined: ', undefined == undefined);
log('null == undefined: ', null == undefined);
log('null === undefined: ', null == undefined);


const a1 = [1, 2, 3];
const a2 = [1, 2, 3];
log('a1 == [1,2,3]: ', a1 == [1, 2, 3]);
log('a1 == a1: ', a1 == a1);
log('a1 === a1: ', a1 === a1);
log('a1 === a2: ', a1 === a2);
log('a1 == a2: ', a1 == a2);
log('flatEquals(a1,a2):', a1.length == a2.length && a1.every((v, i) => v === a2[i]));


let firstJohn = { person: "John" }
let secondJohn = { person: "John" }
let basedOnFirstJohn = Object.create(firstJohn);
log('firstJohn == firstJohn: ', firstJohn == firstJohn);
log('firstJohn === secondJohn: ', firstJohn === secondJohn);
log('firstJohn == secondJohn: ', firstJohn == secondJohn);
log('firstJohn == basedOnFirstJohn: ', firstJohn == basedOnFirstJohn);
log('firstJohn === basedOnFirstJohn: ', firstJohn === basedOnFirstJohn);

let sym1 = Symbol("1"); // a unique and immutable primitive value 
log(sym1, sym1, "===", sym1 === sym1); // true
let sym2 = Symbol("1");
let objValues = { sym1: "value1", sym2: "value2" };
log(objValues);
let obj2Values = { [sym1]: "value1", [sym2]: "value2" };
log(objValues, " === ", obj2Values, " vs. ", objValues === obj2Values);
let obj1Value = { [sym1]: "value1", [sym1]: "value2" };
log(obj2Values, " vs. ", obj1Value);
log(sym1, sym2, "===", sym1 === sym2); // false
log(sym1, sym2, "==", sym1 == sym2); // false
log(Symbol.for("1"), sym1, "==", Symbol.for("1") === sym1);


process.stdin.on('data', () => {
    const args = messages.dequeue();
    for (const arg of args) {
        process.stdout.write(String(arg));
        process.stdout.write(' ');
    }
    if (messages.isEmpty()) {
        process.exit();
    }
});
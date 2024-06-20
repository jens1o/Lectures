let i = 1;      // double-precision 64-bit binary IEEE 754 value
let f = 1.0;    // double-precision 64-bit binary IEEE 754 value
console.log(
    Number.MIN_VALUE,
    Number.MIN_SAFE_INTEGER,
    Number.MAX_SAFE_INTEGER,
    Number.MAX_VALUE);
let ib = 1n;    // Number.MAX_SAFE_INTEGER 9007199254740991n
console.log(100n === BigInt(100));
let x = NaN;
let y = Infinity;
let z = -Infinity;

let b = true; // oder false
console.log("Boolean(undefined)", Boolean(undefined));

// we have the standard operators: +, - , *, /, %, ++, --, ** 
// and the bitwise operators: &, |, ^, ~, <<, >>, >>>
console.log("i++ ", i++); // 1 oder 2? 
console.log("++i ", ++i); // 2 oder 3?
console.log("2 ** 4 ", 2 ** 4);
console.log("7 % 3 ", 7 % 3);


let _s = "42";
console.log('Die Antwort ist ' + _s); // Template literals (Template strings)
console.log(`Die Antwort ist ${_s}.`); // Template literals (Template strings)
console.log(`
    Die Antwort mag ${_s} sein, 
    aber was ist die Frage?`);

console.log(String(42)); // "42"


let anonymousObj = {
    i: 1, 
    u: { j: 2, v: { k: 3 } }, 
    toString: function () { return "anonymousObj"; }
};

// Zugriff auf die Eigenschaften eines Objekts
anonymousObj.j = 2;     // mittels Bezeichner ("j") (eng. Identifier)
anonymousObj["j"] = 4;  // mittels String ("j")
anonymousObj["k"] = 3;
console.log("anonymousObj", anonymousObj);
console.log("anonymousObj.toString()", anonymousObj.toString());
delete anonymousObj.toString;
console.log("anonymousObj.toString() [original]", anonymousObj.toString());
console.log("anonymousObj.u?.v.k", anonymousObj.u?.v.k); // Chain-Operator
console.log("anonymousObj.u.v?.k", anonymousObj.u.v?.k);
console.log("anonymousObj.u.q?.k", anonymousObj.u.q?.k);
console.log("anonymousObj.p?.v.k", anonymousObj.p?.v.k);

let date = new Date("8.6.2024") // ACHTUNG: Locale-Settings
console.log(date);

let $a = [1];

let emptyObject = null;

let func = function () { return "Hello World"; };
console.log(func, func());

let sym1 = Symbol("1"); // a unique and immutable primitive value 
let sym2 = Symbol("1");
let obj1Values = { sym1: "value1", sym2: "value2" };
console.log(obj1Values);
console.log("sym1 in obj1Values: ", sym1 in obj1Values);
let obj2Values = { [sym1]: "value1", [sym2]: "value2" };
console.log("sym1 in obj2Values: ", sym1 in obj2Values);
console.log(obj1Values, " vs. ", obj2Values);

let u = undefined;


// We have the standard logical operators: &&, ||, ! and also ??

/* Operator Madness */
console.log("1 && \"1\": ", 1 && '1');
console.log("null && \"1\": ", null && '1');
console.log("null && true: ", null && true);
console.log("true && null: ", true && null);
console.log("null && false: ", null && false);
console.log("{} && true: ", {} && true);

// nullish coalescing operator (??) (vergleichbar zu ||)
console.log("1 ?? \"1\": ", 1 ?? '1');
console.log("null ?? \"1\": ", null ?? '1');
console.log("null ?? true: ", null ?? true);
console.log("true ?? null: ", true ?? null);
console.log("null ?? false: ", null ?? false);
console.log("{} ?? true: ", {} ?? true);

// Nützliche Zuweisungen

anonymousObj.name ||= "Max Mustermann"
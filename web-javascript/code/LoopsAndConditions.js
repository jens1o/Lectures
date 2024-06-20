'use strict';

const arr = [1, 3, 4, 7, 11, 18, 29];

console.log("\If-elseif-else:");
if (arr.length == 7) {
    console.log("arr.length == 7");
} else if (arr.length < 7) {
    console.log("arr.length < 7");
} else {
    console.log("arr.length > 7");
}

console.log("\nSwitch:");
switch (arr.length) {
    case 7:
        console.log("arr.length == 7");
        break;
    case 6:
        console.log("arr.length == 6");
        break;
    default:
        console.log("arr.length != 6 and != 7");
}

switch ("foo") {
    case "bar":
        console.log("it's bar");
        break;
    case "foo":
        console.log("it's foo");
        break;
    default:
        console.log("not foo, not bar");
}

switch (1) { // Vergleich auf strikte Gleichheit (===)
    case "1":
        console.log("string(1)");
        break;
    case 1:
        console.log("number(1)");
        break;
}



console.log("\nContinue:");
for (let i = 0; i < arr.length; i++) {
    const v = arr[i];
    if (v % 2 == 0) continue;
    console.log(v);
}

console.log("\nBreak with label:");
outer: for (let i = 0; i < arr.length; i++) {
    for (let j = 0 ; j < i; j++) {
        if (j == 3) break outer;
        console.log(arr[i], arr[j]);
    }
}

console.log("\nin (properties of Arrays):");
for (const key in arr) {
    console.log(key, arr[key]);
}

console.log("\nof (values of Arrays):");
for (const value of arr) {
    console.log(value);
}

console.log("\nArray and Objects - instanceof:");
console.log("arr instanceof Object", arr instanceof Object);
console.log("arr instanceof Array", arr instanceof Array);

const obj = {
    name: "John",
    age: 30,
    city: "Berlin"
};

console.log("\nin (properties of Objects):");
for (const key in obj) {
    console.log(key, obj[key]);
}

/* TypeError: obj is not iterable
for (const value of obj) {
    console.log(value);
}
*/


{
    console.log("\nIteration über Iterables (here: Map):");
    const m = new Map();
    m.set("name", "Elisabeth");
    m.set("alter", 50);
    console.log("Properties of m: ");
    for (const key in m) {
        console.log(key, m[key]);
    }
    console.log("Values of m: ");
    for (const [key, value] of m) {
        console.log(key, value);
    }
}

{
    console.log("\nWhile Loop: ");
    let c = 0;
    while (c < arr.length) {
        const v = arr[c]
        if (v > 10) break;
        console.log(v);
        c++;
    }
}


{
    console.log("\nDo-While Loop: ");
    let c = 0
    do {
        console.log(arr[c]);
        c++;
    } while (c < arr.length);
}

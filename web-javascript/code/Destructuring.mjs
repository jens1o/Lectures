import { log, ilog, done } from "./log.mjs";

log("Array Destructuring:");

let [val1,val2] = [1, 2, 3, 4]; 
ilog("[val1, val2] = [1, 2, 3, 4]:", "val1:", val1, ", val2:", val2); // 1



log("Object Destructuring:");

let { a, b } = { a: "aaa", b: "bbb" };
ilog('let { a, b } = { a: "aaa", b: "bbb" }: ', "a:", a, ", b:", b); // 1

let { a: x, b: y } = { a: "a", b: "b" }; 
ilog('let { a: x, b: y } = { a: "a", b: "b" }: ', "x:", x, ", y:", y); // 1

let { a: u, b: v, ...w } = { a: "+", b: "-", c: "*", d: "/" }; 
ilog(
  'let { a: u, b: v, ...w } = { a: "+", b: "-", c: "*", d: "/" }:',
  "u:",
  u,
  ", v:",
  v,
  ", w:",
  w,
); // + - {c: "*", d: "/"}

let { k1, k2 } = { a: "a", b: "b" }; 
ilog('let { k1, k2 } = { a: "a", b: "b" }:', "k1:", k1, ", k2:", k2); 
// "undefined undefined", weder k1 noch k2 sind definiert

done();

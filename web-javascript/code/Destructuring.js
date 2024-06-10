let [a,b] = [1,2,3,4]; // array destructuring
console.log(a,b); // 1


let {a : x, b : y} = {a: "a", b: "b"};  // object destructuring
console.log(x,y); // 1
let {a : u, b : v, ...w} = {a: "+", b: "-", c :"*", d:"/"};  // object destructuring
console.log(u,v,w); // + - {c: "*", d: "/"}

let {k1 , k2} = {a: "a", b: "b"};  // object destructuring
console.log(k1,k2); // undefined undefined // k1 and k2 are not defined in the object

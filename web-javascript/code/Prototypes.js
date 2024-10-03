// Prototypen
console.log("{}.__proto__: ",{}.__proto__);
console.log("Array.prototype: ",Array.prototype);
console.log("Array.prototype.__proto__: ",Array.prototype.__proto__);
console.log("Object.prototype: ",Object.prototype);
console.log("Object.__proto__: ",Object.__proto__);

let o = { created: "long ago" };
var p = Object.create(o);
console.log("Object.getPrototypeOf(o): " + Object.getPrototypeOf(o));
console.log("o.isPrototypeOf(p):" + o.isPrototypeOf(p));
console.log("Object.prototype.isPrototypeOf(p): " + Object.prototype.isPrototypeOf(p));

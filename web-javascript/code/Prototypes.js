console.log({}.__proto__);
console.log(Array.prototype);
console.log(Array.prototype.__proto__);
console.log(Object.prototype);
console.log(Object.__proto__);

try {
  let a = [1];
  console.log(a.fold());
} catch (error) {
  console.log("error: ", error.message);
}

// ADDING FUNCTIONS TO Array.prototpye IS NOT RECOMMENDED!
// IF ECMAScript EVENTUALLY ADDS THIS METHOD (I.E. fold)
// TO THE PROTOTYPE OF ARRAY OBJECTS, IT MAY CAUSE HAVOC.
Array.prototype.fold = function (f) {
  if (this.length === 0) {
    throw new Error("array is empty");
  } else if (this.length === 1) {
    return this[0];
  } else {
    let result = this[0];
    for (let i = 1; i < this.length; i++) {
      result = f(result, this[i]);
    }
    return result;
  }
};

let a = [1, 10, 100, 1000];
console.log(a.fold((u, v) => u + v));

let o = { created: "long ago" };
var p = Object.create(o);
console.log(Object.getPrototypeOf(o));
console.log(o.isPrototypeOf(p));
console.log(Object.prototype.isPrototypeOf(p));

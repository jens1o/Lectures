console.log({}.__proto__)
console.log(Array.prototype)
console.log(Array.prototype.__proto__)
console.log(Array.prototype)

try {
    let a = [1];
    console.log(a.fold());
} catch (error) {
    console.log("error: ", error.message)   
}

// THIS IS NOT RECOMMENDED!
// IF ECMAScript EVENTUALLY ADDS THIS FUNCTIONALITY, 
// IT MAY CAUSE HAVOC.
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
}

let a = [1,10,100,1000];
console.log(a.fold((a,b) => a + b));


let o = { created : "long ago"  };
var p = Object.create(o);
console.log(Object.getPrototypeOf(o));
console.log(o.isPrototypeOf(p));
console.log(Object.prototype.isPrototypeOf(p));
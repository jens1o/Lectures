function O() { this.o = "o"; }
O.prototype.getO = function () { return this.o; };
const o = new O();
console.log(o.getO());

console.log(o.__proto__ === O.prototype); // true
console.log(o.__proto__.constructor === O); // true
`use strict`;

// scope is limited to the enclosing function; 
// the definition is hoisted (initialized with undefined); 
// in modern JS, use let or const instead of var!
var x = "x"; 

// scope is limited to the enclosing block; 
// reference before definition throws an error
let y = "y"; 

// scope is limited to the enclosing block
const z = "z"; 


function sumIfDefined(a, b) {
    if (parseInt(a)) {
        var result = parseInt(a); // don't do this in your real code!
    } else {
        result = 0;
    }
    const bVal = parseFloat(b);
    if (bVal) {
        result  += bVal
    }
    return result;
}

console.log(sumIfDefined()); // 0
console.log(sumIfDefined(1)); // 1
console.log(sumIfDefined(1, 2)); // 3
console.log(sumIfDefined(1, "2")); // 3
console.log(sumIfDefined(undefined, "2")); // 2


function global_x() {
    console.log(x,y); 
    
    // const y = ''; // => the previous line throws an error because y is not defined

    console.log(x, y, z); // 1 2 3
}

function local_var_x() {
    console.log(x); 
    // console.log(y); // y is not defined

    var x = 1; // the declaration of var is hoisted, but not the initialization
    let y = 2;
    const z = 3;

    console.log(x, y, z); // 1 2 3
}


console.log("Start:", x, y, z); // 0 0 0
global_x();
local_var_x();


console.log("Last:", x, y, z); // 0 0 0
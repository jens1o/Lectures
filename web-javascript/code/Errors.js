'use strict';

try {
    let i = 1 / 0; // Berechnung erzeugen nie eine Exception
    console.log("i", i);
} catch {
    console.error("division by zero");
} finally {
    console.log("computation finished");
}


try {
    const obj = {};
    obj = { a: 1 };
} catch ({name,message}) {
    console.error(message);
} finally {
    console.log("object access finished");
}


try {
    throw new RangeError("out of range");
} catch (error) {
    if (error instanceof RangeError) {
            console.error("a RangeError");
    } else {
        throw error;
    }    
} finally {
    console.log("error handling finished");
}

class Figure {
    calcArea() {
        throw new Error('calcArea is not implemented');
    }
}
class Rectangle extends Figure {
    height;
    width;

    constructor(height, width) {
        this.height = height;
        this.width = width;
    }

    calcArea() {
        return this.height * this.width;
    }

    get area() {
        return this.calcArea();
    }

    set area(value) {
        throw new Error('Area is read-only');
    }
}

const r = new Rectangle(10, 20);
console.log("r instanceof Figure", r instanceof Figure); // true
console.log(r.width);
console.log(r.height);
console.log(r.area); // 200

try {
    r.area = 300; // Error: Area is read-only
} catch (e) {
    console.log(e.message);
}

class Queue {
    #last = null; 
    #first = null;
    constructor() { }
    enqueue(elem) {
        if (this.#first === null) {
            const c = { e: elem, next: null };
            this.#first = c
            this.#last = c
        } else {
            const c = { e: elem, next: null };
            this.#last.next = c;
            this.#last = c;
        }
    }
    dequeue() {
        if (this.#first === null) {
            return null;
        } else {
            const c = this.#first;
            this.#first = c.next;
            return c.e;
        }
    }
    isEmpty() {
        return this.#first === null;
    }
}
/* requires node.js */ 
module.exports = class Queue {
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
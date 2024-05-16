const xpath = require('xpath');
const dom = require('@xmldom/xmldom').DOMParser;
const fs = require("fs");

const xmlFile = fs.readFileSync(`./ds-xml/code/demo.xml`, "utf8");
const xmlDOM = new dom().parseFromString(xmlFile);
const xpathFile = fs.readFileSync(`./ds-xml/code/demo.xpath`, "utf8");

const namespaces = {
    "co": "http://dhbw-mannheim.de/complex-order",
    "o": "http://dhbw-mannheim.de/order"
}


xpathFile.split("\n").forEach(expr => {
    if (expr.trim() == "" || expr.startsWith("(:")) return;

    const exprFragments = expr.split("⇒");
    const baseExpr = exprFragments[0].trim();
    const perNodeExpr = exprFragments.length > 1 ? exprFragments[1].trim() : undefined;

    console.log(`Evaluating "${baseExpr}":`);
    xpath.
        parse(baseExpr).
        select({ node: xmlDOM, namespaces: namespaces }).
        forEach((element, i) => {
            if (perNodeExpr) {
                console.log(`${i}: Evaluating "${perNodeExpr}" for node "${element.toString()}":\n`);
                let result = xpath.evaluate(
                    perNodeExpr,
                    element,
                    (prefix) => namespaces[prefix],
                    xpath.XPathResult.ANY_TYPE,
                    null);
                let node = result.iterateNext()
                while (node) {
                    console.log(node.toString());
                    node = result.iterateNext();
                }
            } else {
                console.log(`${i}: ${element.toString()}\n`);
                /* verbose
                let node = element;
                console.log(`${i}:`+ node.toString() + " " +node.prefix + " " + node.namespaceURI + " " + node.localName + " " + node.nodeValue + " " + node.textContent + "\n");
                */
            }
        });
    console.log(`\n`);
});

// var obj = JSON.parse(fs.readFileSync('file', 'utf8'));
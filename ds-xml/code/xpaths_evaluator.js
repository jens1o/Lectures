/*
 * Evaluates a set of XPath expressions against an XML file.
 *
 * Usage: node xpaths_evaluator.js <spec.json>
 * 
 * The specification document is a JSON file with the specified JSON schema (see `specSchema` below).
 *
 * @author Michael Eichberg
 */

const specSchema = {
    "title": "XPath Evaluator Specification",
    "description": "A specification for evaluating XPath expressions against an XML file",
    "type": "object",
    "properties": {
        "source": {
            "description": "The path to the XML file.",
            "type": "string",
        },
        "namespaces": {
            "description": "Namespaces used in the XPath expressions; keys are the prefixes, values are the URIs; i.e., the names.",
            "type": "object",
        },
        "xpaths": {
            "type": "array",
            "items": {
                "type": "object",
                "properties": {
                    "expr": "string",
                    "subExpr": "string",
                    "type": "enum ['single','nodeset']"
                },
                "additionalProperties": false,
                "required": ["expr"]
            }
        }
    },
    "required": ["source"]
}

const fs = require("fs");
const xpath = require('xpath');
const dom = require('@xmldom/xmldom').DOMParser;


if (process.argv.length < 3) {
    console.error("Usage: node xpaths_evaluator.js <spec.json>");
    process.exit(1);
}

var spec = JSON.parse(fs.readFileSync(process.argv[2], 'utf8'));

const xmlFile = fs.readFileSync(spec.source, "utf8");
const xmlDOM = new dom().parseFromString(xmlFile);
const namespaces = spec.namespaces ? spec.namespaces : {};
//console.log(`Namespaces: ${JSON.stringify(namespaces)}`);

spec.xpaths.forEach(xpathSpec => {
    const baseExpr = xpathSpec["expr"];
    const subExpr = xpathSpec["subExpr"];

    console.log(`Evaluating "${baseExpr}":`);

    if (xpathSpec["type"] === "single") {
        let xpathNS =  xpath.useNamespaces(namespaces);
        let result = xpathNS(baseExpr, xmlDOM);
        console.log(result);
    } else {
        const result = xpath.
            parse(baseExpr).
            select({ node: xmlDOM, namespaces: namespaces })
        console.log(`Done`)    
        result.
            forEach((element, i) => {
                if (subExpr) {
                    console.log(`${i}: Evaluating "${subExpr}" for node "${element.toString()}":\n`);
                    let result = xpath.evaluate(
                        subExpr,
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
                }
            });
    }
    console.log(`\n`);
});


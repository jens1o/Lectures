#!/bin/zsh

setopt extendedglob

rsync -v -R  **/**(.html|.rst.html.pdf|.webp|.svg|.png|.mov|.jpg|.ipynb)~**/*.dgraph/**~**/*.graffle/**~**/external/**~**/private/** ~/Sites/delors.github.io 
rsync -v -R LectureDoc2/**/**~**/Icon?~**/*.rst~**/*.graffle/** ~/Sites/delors.github.io
rsync -v -R reStructuredTextToLectureDoc2/**/**(.html|.rst.html.pdf|.webp|.svg|.png|.mov|.jpg) ~/Sites/delors.github.io

rst2pdf LectureDoc2/readme.rst ~/Sites/delors.github.io/LectureDoc2/readme.pdf  
#!/bin/zsh

setopt extendedglob

rsync -v -R **/**(.html|.webp|.svg|.png|.mov|.jpg)~**/*.dgraph/**\|**/*.graffle/** ~/Sites/delors.github.io 
rsync -v -R LectureDoc2/**/**~**/Icon\? ~/Sites/delors.github.io
rsync -v -R reStructuredTextToLectureDoc2/**/**(.html|.webp|.svg|.png|.mov|.jpg) ~/Sites/delors.github.io
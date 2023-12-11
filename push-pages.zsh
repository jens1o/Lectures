#!/bin/zsh

rsync -R **/**(.html|.webp|.svg|.png|.mov|.jpg) ~/Sites/delors.github.io 
rsync -R LectureDoc2/**/** ~/Sites/delors.github.io
rsync -R reStructuredTextToLectureDoc2/**/**(.html|.webp|.svg|.png|.mov|.jpg) ~/Sites/delors.github.io
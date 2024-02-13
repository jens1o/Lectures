#!/bin/zsh 

set -e

function update_pdf_if_necessary() {
    pdf_file="$1.pdf"   
    if [[ ! -f "$pdf_file" || "$pdf_file" -ot "$1" ]]
    then
        echo "$(date '+%Y-%m-%d %H:%M:%S') updating:" $pdf_file 
        osascript LectureDoc2/gen-pdf-from-slides.applescript "$1" 
    fi
}

function check_all_rst_html_files() {
    for f in **/*.rst.html 
    do
        update_pdf_if_necessary "$PWD/$f"
    done
}

# Checks for each all ".rst.html" file if the corresponding pdf file
# needs to be updated.
check_all_rst_html_files

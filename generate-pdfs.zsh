#!/bin/zsh 

target_directory="/Users/Michael/Sites/delors.github.io/" # must end with "/"!

set -e

function update_pdf_if_necessary() {
    pdf_path="$1.pdf"   
    if [[ ! -f "$pdf_path" || "$pdf_path" -ot "$1" ]]
    then
        echo "$(date '+%Y-%m-%d %H:%M:%S') updating:" $pdf_path 
        osascript LectureDoc2/gen-pdf-from-slides.applescript "$1"
        sleep 1 # required to ensure that the file is acutally written to disk
        # pdf_file=$(basename "$pdf_path")
    fi
    rsync -ac --relative "$pdf_path" "$target_directory"
}

function check_all_rst_html_files() {
    for f in **/*.rst.html 
    do
        update_pdf_if_necessary "$f"
    done
}

# Checks for each ".rst.html" file if the corresponding pdf file
# needs to be updated.
check_all_rst_html_files

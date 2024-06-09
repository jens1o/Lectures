#!/bin/zsh 

function update_html_if_necessary() {
    html_file="$1.html"   
    language_option=""
    if [[ "$1" == *".de.rst" ]]
    then
        lang=$(echo "$1" | grep -Eo '\.([a-z]{2,3})\.rst$' | grep -Eo "[a-z]+\." | sed -E 's/\.//g')
        language_option="--language=$lang"
    fi
    if [[ ! -f "$html_file" || "$html_file" -ot "$1" ]]
    then
        path_prefix=$(echo "$1" | sed -E 's/[.0-9a-zA-Z_-]+/../g' | grep -Eo "^(\.+/)+")
        if [ $? -ne 0 ]
        then
            path_prefix=""
        fi
        echo "$(date '+%Y-%m-%d %H:%M:%S') updating:" $html_file 
        reStructuredTextToLectureDoc2/rst2ld.py "$1" \
            --output "$html_file" \
            --ld-path $path_prefix"LectureDoc2" \
            --ld-exercises-passwords "$html_file.passwords.txt" \
            $language_option
    fi
}

function check_all_rst_files() {
    for f in **/*.rst # [0-9]*.rst
    do
        update_html_if_necessary "$f"
    done
}

echo "Checks every couple of seconds if some file needs to be regenerated."
echo "Press CTRL+C to terminate"
while true
do
    check_all_rst_files

    sleep 3
done

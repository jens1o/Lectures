#!/bin/zsh 

# The following script checks every couple of seconds if an rst file was updated.
# If so, it updates the corresponding html file.
# It also processes all .publish files in the current directory and copies the 
# files listed in them to the specified target directory (see below).

target_directory="/Users/Michael/Sites/delors.github.io/" # must end with "/"!

# Use the following lines to enable/disable debug output:
# set -x
# set +x

function update_html_if_necessary() {
    html_file="$1.html"   
    language_option=""
    if [[ "$1" == *"."*".rst" ]]
    then
        lang=$(\
            echo "$1" |\
            grep -Eo '\.([a-z]{2,3})\.rst$' |\
            grep -Eo "[a-z]+\." |\
            sed -E 's/\.//g')
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
            --link-stylesheet \
            $language_option
            
    fi
}

function check_all_rst_files() {
    for f in **/*.rst # [0-9]*.rst
    do
        update_html_if_necessary "$f"
    done
}

function process_all_publish_files_in_subfolders() {
    for f in .publish */.publish
    do
        path_to_publish=$(dirname "$f")
        #set -x
        rsync -a "$path_to_publish" --files-from="$f" "$target_directory$path_to_publish/" 
        #set +x
    done
}   

function process_publish_file_in_root_folder() {
    f=".publish"
    path_to_publish=$(dirname "$f")
    removed_files=ls -p  "$target_directory" | grep -v / | grep -F -v -x -f .publish
    for file in $removed_files
    do
        target_file="$target_directory$file"
        echo "$(date '+%Y-%m-%d %H:%M:%S') removing:" $target_file
        rm $target_file
    done
    #set -x
    rsync -a "$path_to_publish" --files-from="$f" "$target_directory$path_to_publish/"
    #set +x
}   

echo "Checks every couple of seconds if an rst file was updated."
echo "Press CTRL+C to terminate."
while true
do
    check_all_rst_files
    process_publish_file_in_root_folder
    process_all_publish_files_in_subfolders


    sleep 3
    # echo "check done:"$(date '+%Y-%m-%d %H:%M:%S')
done
